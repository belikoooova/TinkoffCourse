package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.task1.ClientServerUtils.PORT;
import static edu.hw8.task1.ClientServerUtils.SERVER_ADDRESS;

@SuppressWarnings("UncommentedMain")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Server {
    @Setter
    private static final Logger LOGGER = LogManager.getLogger();
    public static final PhrasesDictionary DICTIONARY = new InMemoryPhrasesDictionary();
    private static final int MAX_CONNECTIONS = 10;
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    @Getter
    private static final AtomicInteger ACTIVE_CONNECTIONS = new AtomicInteger(0);

    @SneakyThrows
    public static void main(String[] args) {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(SERVER_ADDRESS, PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        if (ACTIVE_CONNECTIONS.get() < MAX_CONNECTIONS) {
                            SocketChannel client = serverSocketChannel.accept();
                            client.configureBlocking(false);
                            ClientHandler handler = new ClientHandler(client, DICTIONARY);
                            SERVICE.submit(handler);
                            ACTIVE_CONNECTIONS.incrementAndGet();
                        } else {
                            LOGGER.info("Client is waiting.");
                            // For test
                            throw new RuntimeException("Too much clients.");
                        }
                    }

                    iter.remove();
                }
            }
        } finally {
            SERVICE.shutdown();
        }
    }
}
