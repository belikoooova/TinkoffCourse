package edu.hw8.task1;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientServerUtils {
    static final String SERVER_ADDRESS = "localhost";
    static final int PORT = 18080;
    static final Charset CHARSET = StandardCharsets.UTF_16;

    @SneakyThrows
    public static String readMessage(ByteBuffer buffer, SocketChannel channel) {
        StringBuffer message = new StringBuffer();
        while (message.isEmpty() || message.charAt(message.length() - 1) != '\n') {
            buffer.flip();
            message.append(CHARSET.decode(buffer));
            buffer.clear();
            channel.read(buffer);
        }
        buffer.clear();
        return message.toString();
    }
}
