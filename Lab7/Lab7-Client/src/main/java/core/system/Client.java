package core.system;

import core.managers.AuthManager;
import shared.enteties.Movie;
import shared.enteties.User;
import shared.exceptions.InvalidInputException;
import core.managers.InputManager;
import shared.Serializers.ClientSideSerializer;
import shared.serializables.*;
import shared.sort.SortByName;

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
        startConnection(host, port);
    }

    private User user;


    public void run() {

        Scanner scanner = new Scanner(System.in);

        // Список всех команд
        List<CommandInfoObject> commands;
        ServerRequest firstConnection = new ServerRequest("connection");
        commands = sendRequest(firstConnection).getCommands();

        AuthManager authData = new AuthManager();
        ServerRequest authRequest = new ServerRequest("auth", authData.getUser(), authData.getType());
        ServerResponse authResponse = sendRequest(authRequest);
        if (authResponse.getUser() != null) {
            this.user = authResponse.getUser();
        } else {
            System.out.println(authResponse.getResponse().getErrorMessage());
            closeConnection();
        }
//        printResponse(authResponse.getResponse());

        System.out.println("You're connected to the server!");

        while (true) {
            try {
                InputManager input = new InputManager(scanner, commands);
                List<ServerRequest> requests = input.getRequest();
                requests.forEach(request -> {
                    request.setUser(this.user);
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
                                scriptRequest.setUser(this.user);
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

                    printResponse(response.getResponse());
                });

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println(e.getMessage());
                System.out.println("You missed some params!");
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
//            e.printStackTrace();
            System.out.println("Server is not available");
            closeConnection();
            return new ServerResponse(new ResponseBody("Server is not available"));
        }
    }


    private void startConnection(String host, int port) {
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

    private void printResponse(ResponseBody response) {
        if (response.getErrorMessage() != null) {
            System.out.println(response.getErrorMessage());
            return;
        }
        if (response.getpResponse() != null) {
            Stream.of(response.getpResponse()).forEach(System.out::println);
            return;
        }
        List<Movie> movies = new ArrayList<>(response.getcResponse().stream().toList());
        movies.sort(new SortByName());
        movies.forEach(movie -> System.out.println(movie.toString()));
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
