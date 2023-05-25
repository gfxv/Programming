package shared.Serializers;

import shared.serializables.ServerRequest;
import shared.serializables.ServerResponse;

import java.io.*;
import java.nio.ByteBuffer;

public class ServerSideSerializer {

    public static ServerRequest deserialize(ByteBuffer byteBuffer) throws IOException, ClassNotFoundException {
        byteBuffer.flip();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array()));
        return (ServerRequest) objectInputStream.readObject();
    }

    public static byte[] serialize(ServerResponse request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(request);
        outputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

}
