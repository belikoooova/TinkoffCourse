package edu.hw8;

import edu.hw8.task1.ClientHandler;
import edu.hw8.task1.ClientServerUtils;
import edu.hw8.task1.PhrasesDictionary;
import edu.hw8.task1.Server;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.mockito.MockedStatic;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import static edu.hw8.task1.ClientServerUtils.readMessage;
import static edu.hw8.task1.Server.DICTIONARY;
import static edu.hw8.task1.Server.SERVICE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class Task1Test {
    private static final int MAX_CONNECTIONS = 10;

    @SneakyThrows
    @Test
    void testClientHandler() {
        try (MockedStatic<ClientServerUtils> utilsMock = mockStatic(ClientServerUtils.class)) {
            // given
            SocketChannel mockChannel = mock(SocketChannel.class);
            PhrasesDictionary mockDictionary = mock(PhrasesDictionary.class);
            ByteBuffer buffer = ByteBuffer.wrap(new byte[256]);
            String testRequest = "тест\n";
            String testResponse = "ответ";

            // when
            utilsMock.when(() -> readMessage(buffer, mockChannel))
                .thenReturn(testRequest)
                .thenReturn(null);
            when(mockDictionary.getReply(testRequest.substring(0, testRequest.length() - 1))).thenReturn(testResponse);

            ClientHandler handler = new ClientHandler(mockChannel, mockDictionary);
            handler.run();

            // then
            verify(mockChannel, atLeastOnce()).write(any(ByteBuffer.class));
        }
    }

    @SneakyThrows
    @Test
    void testConnectionLimit() {
        ServerSocketChannel mockServerSocketChannel = mock(ServerSocketChannel.class);
        SocketChannel mockSocketChannel = mock(SocketChannel.class);

        Server.getActiveConnections().set(MAX_CONNECTIONS);

        when(mockServerSocketChannel.accept()).thenReturn(mockSocketChannel);

        assertThrows(RuntimeException.class, () -> {
            if (Server.getActiveConnections().get() < MAX_CONNECTIONS) {
                SocketChannel serverSocketChannel = mockServerSocketChannel.accept();
                serverSocketChannel.configureBlocking(false);
                ClientHandler handler = new ClientHandler(serverSocketChannel, DICTIONARY);
                SERVICE.submit(handler);
                Server.getActiveConnections().incrementAndGet();
            } else {
                throw new RuntimeException("Too much clients.");
            }
        });

        Server.getActiveConnections().set(0);
    }
}
