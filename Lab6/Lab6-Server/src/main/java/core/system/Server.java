package core.system;

import shared.Serializers.ServerSideSerializer;
import shared.serializables.ServerRequest;
import shared.serializables.ServerResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    private CommandInfoCollection commandInfoCollection;
    private ServerSocketChannel serverSocket;
    private Selector selector;
    private Executor executor;
    private boolean connectedUser = false;


    public Server(String host) {
        executor = new Executor();
        commandInfoCollection = new CommandInfoCollection();

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
                    else if (key.isReadable()) readRequest(key, selector);
                    else if (key.isWritable()) key.channel().register(selector, SelectionKey.OP_READ);

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
                channel.close();
                return;
            }

            ServerRequest request = ServerSideSerializer.deserialize(buffer);

            if (request.getCommand().equals("connection")) {
                ServerResponse con = new ServerResponse(commandInfoCollection.getCommandInfoObjects());
                sendResponse(channel, con);
                buffer.clear();
                channel.register(selector, SelectionKey.OP_WRITE, ServerSideSerializer.serialize(con));
                return;
            }

            buffer.clear();
            ServerResponse response = executor.executeCommand(request);
            sendResponse(channel, response);
            channel.register(selector, SelectionKey.OP_WRITE, ServerSideSerializer.serialize(response));
        } catch (IOException e) {
//            e.printStackTrace(); // to get serialVersionUID
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
