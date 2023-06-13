package core.system;

import core.managers.AuthManager;
import core.managers.LogManager;
import shared.Serializers.ServerSideSerializer;
import shared.serializables.ServerRequest;
import shared.serializables.ServerResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final String dbURL = "jdbc:postgresql://127.0.0.1:5432/studs";
    private CommandInfoCollection commandInfoCollection;
    private static ServerSocketChannel serverSocket;
    private Selector selector;
    private Executor executor;
    private ExecutorService fixedPool;
    Connection connection;
    LogManager log;


    public Server(String host) throws SQLException {
        executor = new Executor();
        commandInfoCollection = new CommandInfoCollection();
        this.log = new LogManager();

        int port = 6666;
        boolean running = false;
        while (!running) {
            try {
                serverSocket = ServerSocketChannel.open();
                serverSocket.socket().bind(new InetSocketAddress(port));
                serverSocket.configureBlocking(false);

                this.selector = Selector.open();
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);
                running = true;
            } catch (IOException e) {
                port++;
            }
        }

        Properties info = new Properties();
        try {
            info.load(new FileInputStream("./src/main/resources/db.cfg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection(dbURL, info);
        Config.setConnection(connection);

        this.fixedPool = Executors.newFixedThreadPool(10);

        System.out.printf("Server is up and running on %s:%d\n", host, port);
        System.out.println("Waiting for clients...");
    }

    public void start() {
        try {
            while (true) {
                if (selector.select(5000) == 0) continue;
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        SocketChannel channel = serverSocket.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.println("New connection!");
                    }
                    else if (key.isReadable()) {
                        Runnable rq = () -> {
                            try {
                                readRequest(key, selector);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        };
                        this.fixedPool.execute(rq);
                    }
                    else if (key.isWritable()) key.channel().register(selector, SelectionKey.OP_READ);
                    else {
                        key.channel().close();
                    }

                    selector.selectedKeys().remove(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readRequest(SelectionKey key, Selector selector) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(65536);
        try {
            int readBytes = channel.read(buffer);
            if (readBytes == -1) {
                System.out.println("Client disconnected");
                channel.close();
                return;
            }
            if (buffer.position() == 0) {
                return;
            }

            ServerRequest request = ServerSideSerializer.deserialize(buffer);

            if (request.getCommand().equals("connection")) {
                ServerResponse con = new ServerResponse(commandInfoCollection.getCommandInfoObjects());
                sendResponse(channel, con);
                buffer.clear();
                channel.register(selector, SelectionKey.OP_WRITE, ServerSideSerializer.serialize(con));
                return;
            } else if (request.getCommand().equals("exit")) {
                executor.executeCommand(new ServerRequest("save"));
            } else if (request.getCommand().equals("auth")) {
                ServerResponse auth = new AuthManager(request, connection).auth();
                sendResponse(channel, auth);
                buffer.clear();
                channel.register(selector, SelectionKey.OP_WRITE, ServerSideSerializer.serialize(auth));
                return;
            }

            log.commandLog(request.getUser(), request.getCommand());
            buffer.clear();
            ServerResponse response = executor.executeCommand(request);
            sendResponse(channel, response);
            channel.register(selector, SelectionKey.OP_WRITE, ServerSideSerializer.serialize(response));
        } catch (ClosedChannelException ignored) {
        } catch (IOException e) {
            e.printStackTrace(); // to get serialVersionUID
            System.out.println("Client disconnected");
            channel.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendResponse(SocketChannel socketChannel, ServerResponse response) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(65536);
        byte[] serializedResponse = ServerSideSerializer.serialize(response);
        buffer.put(serializedResponse);
        buffer.flip(); // Режим запись
        socketChannel.write(buffer);
    }

    public void stop() {
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
