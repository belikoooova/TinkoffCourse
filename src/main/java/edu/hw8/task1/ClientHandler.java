package edu.hw8.task1;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import static edu.hw8.task1.ClientServerUtils.readMessage;

@AllArgsConstructor
public class ClientHandler implements Runnable {
    private static final int BUFFER_CAPACITY = 256;
    private final SocketChannel client;
    private final PhrasesDictionary dictionary;

    @SneakyThrows
    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            while (client.read(buffer) != -1) {
                String request = readMessage(buffer, client);
                if (request == null) {
                    break;
                }
                String response = dictionary.getReply(request.substring(0, request.length() - 1));
                client.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_16)));
                buffer.clear();
            }
        } finally {
            client.close();
            Server.getACTIVE_CONNECTIONS().decrementAndGet();
        }
    }
}
