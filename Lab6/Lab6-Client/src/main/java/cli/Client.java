package cli;

import core.enteties.Movie;
import core.exceptions.InvalidInputException;
import core.managers.InputManager;
import shared.serializables.*;

import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;


public class Client {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection(String ip, int port) throws IOException, ClassNotFoundException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());

        Scanner scanner = new Scanner(System.in);

        // Список всех команд
        CommandsInfoResponse commandsAsResponse = (CommandsInfoResponse) in.readObject();
        List<CommandInfoObject> commands = commandsAsResponse.getCommands();

        // Вывод команды help
        ServerResponse helpCommandResponse = (ServerResponse) in.readObject();
        String[] helpCommand = helpCommandResponse.getResponse();

        System.out.println("You are connected to the server!");
        for (String s : helpCommand) {
            System.out.println(s);
        }

        while (clientSocket.isConnected()) {

            System.out.println("Type \"help\" to list all available commands");
            System.out.print(">>> ");
            String[] input;
            input = scanner.nextLine().split(" ");

            String userCommand = input[0];
            String userArgs = "";
            if (input.length == 2) userArgs = input[1];
            boolean validCommand = false;
            CommandInfoObject command = null;
            for (CommandInfoObject cmd : commands) {
                if (cmd.getCommand().equals(userCommand)) {
                    validCommand = true;
                    command = cmd;
                    System.out.println(command.getCommand());
                    break;
                }
            }

            if (validCommand) {
                ServerRequest request = null;
                if (command.hasPrimitiveArg()) request = new ServerRequest(userCommand, userArgs);
                if (command.hasComplexArg()) {
                    try {
                        Movie movie = new InputManager().getMovie();
                        request = new ServerRequest(userCommand, movie);

                    } catch (InvalidInputException e) {
                        System.out.println("invalid input...");
                    }
                }
                if (!command.hasPrimitiveArg() && !command.hasComplexArg()) request = new ServerRequest(userCommand);

                try {
                    ServerResponse response = sendCommandRequest(request);
                    if (response.hasErrors()) {
                        System.out.println(response.getErrorMessage());
                        throw new IOException();
                    }
                    for (String s : response.getResponse()) {
                        System.out.println(s);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("An error occurred while receiving response.");
                    System.out.println("Disconnecting...");
                    closeConnection();
                } catch (Exception e) {
                    System.out.println(123);
                }


//                try {
//                    History.addCommandToHistory(userCommand);
//                  c
//                    send(userCommand).execute(userArgs);
//                } catch (InvalidInputException e) {
//                    System.out.println(e.getMessage());
//                } catch (NoSuchElementException e) {
//                    System.out.println("Bye!");
//                    System.exit(0);
//                } catch (Exception ignore) {}
//            }
            } else {
                System.out.println("I don't know such command :(");
            }
//            System.out.println();
        }
    }


    public ServerResponse sendCommandRequest(ServerRequest request) throws Exception, IOException, ClassNotFoundException{
        out.writeObject(request);
        ServerResponse response = (ServerResponse) in.readObject();
        if (response.hasErrors()) {
            throw new Exception(response.getErrorMessage()); // TODO: throw more specific exception
        }
        return response;
    }


    public void closeConnection() {
        try {
            if (clientSocket != null) clientSocket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


//        String response = cli.sendMessage(new Message("sayHello", "qwe"));
//        System.out.println(response);
    }

}
