import core.system.CommandHandler;
import core.system.CommandInfoCollection;
import core.system.Invoker;
import shared.serializables.CommandsInfoResponse;
import shared.serializables.ServerRequest;
import shared.serializables.ServerResponse;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private CommandInfoCollection commandInfoCollection;

    public void start(int port) {
        Invoker.init();
        commandInfoCollection = new CommandInfoCollection();
        commandInfoCollection.print();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is up and running on port: " + port);
            System.out.println("Waiting for clients...");
            while(!serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                CommandsInfoResponse commandInfo = new CommandsInfoResponse();
                commandInfo.setInfo(commandInfoCollection.getCommandInfoObjects());
                out.writeObject(commandInfo);

                ServerResponse helpCommand = new CommandHandler().run(new ServerRequest("help"));
                out.writeObject(helpCommand);

                System.out.println("Client successfully connected!");
                while (clientSocket.isConnected()) {
                    try {
                        ServerRequest message = (ServerRequest) in.readObject();
                        ServerResponse response = new CommandHandler().run(message);
                        out.writeObject(response);
                        out.flush();
                    } catch (EOFException e) {
                        System.out.println("Client disconnected.");
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            stop();
        }
    }

    public void stop() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
