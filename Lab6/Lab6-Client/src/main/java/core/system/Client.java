package core.system;

import shared.exceptions.InvalidInputException;
import core.managers.InputManager;
import shared.Serializers.ClientSideSerializer;
import shared.serializables.*;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.stream.Stream;


public class Client {

    private static final int BUFFER_SIZE = 65536;
    private SocketChannel client;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    public Client(String host, int port) {
        try {
            while (true) {
                try {
                    InetSocketAddress address = new InetSocketAddress(host, port);
                    client = SocketChannel.open();
                    client.connect(address);
                    break;
                } catch (IOException e) {
                    System.out.println("Reconnecting to server...");
                    Thread.sleep(5000); // 5 second
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid PORT");
                    System.exit(0);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void startConnection() {



        Scanner scanner = new Scanner(System.in);

        // Список всех команд
        List<CommandInfoObject> commands;
        ServerRequest firstConnection = new ServerRequest("connection");
        commands = sendRequest(firstConnection).getCommands();

        while (true) {
            try {
                InputManager input = new InputManager(scanner, commands);
                List<ServerRequest> requests = input.getRequest();
                requests.forEach(request -> {
                    if (request.getCommand().equals("exit")) {
                        ServerResponse exitResponse = sendRequest(request);
                        printResponse(exitResponse.getResponse());
                        closeConnection();
                    }
                    if (request.getCommand().equals("execute_script")) {
                        try {
                            InputManager fileInput = new InputManager(new Scanner(new File(request.getPrimitiveArg())), commands, true);
                            List<ServerRequest> scriptRequests = fileInput.getRequest();
                            scriptRequests.forEach(scriptRequest -> {
                                if (scriptRequest.getCommand().equals("exit")) closeConnection();
                                if (scriptRequest.getCommand().equals("execute_script")) return;
                                ServerResponse response = sendRequest(scriptRequest);
                                if (response.hasErrors()) {
                                    System.out.println(response.getErrorMessage());
                                }
                                printResponse(response.getResponse());
                            });
                        } catch (InvalidInputException e) {
                            System.out.println(e.getMessage());
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found");
                        }
                    }
                    ServerResponse response = sendRequest(request);
                    if (response.hasErrors()) {
                        System.out.println(response.getErrorMessage());
                    }
                    printResponse(response.getResponse());
                });

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public ServerResponse sendRequest(ServerRequest request) {
        try {
            ByteBuffer response = ByteBuffer.allocate(1000000);
            response.clear();
            byteBuffer = ByteBuffer.wrap(ClientSideSerializer.serialize(request));
            client.write(byteBuffer);
            client.read(response);
            return ClientSideSerializer.deserialize(response);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
//            System.out.println("Server is not available");
        }
        return new ServerResponse("Something went wrong");
    }


    private void printResponse(ResponseBody response) {
        if (response.getErrorMessage() != null) {
            System.out.println(response.getErrorMessage());
            return;
        }
        if (response.getpResponse() != null) {
            Stream.of(response.getpResponse()).forEach(System.out::println);
            return;
        }
        response.getcResponse().forEach(movie -> System.out.println(movie.toString()));
    }

    public void closeConnection() {
        try {
            if (client != null) client.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
