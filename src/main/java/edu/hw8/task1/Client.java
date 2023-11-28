package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.task1.ClientServerUtils.PORT;
import static edu.hw8.task1.ClientServerUtils.SERVER_ADDRESS;
import static edu.hw8.task1.ClientServerUtils.readMessage;

@SuppressWarnings("UncommentedMain")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Client {
    private final static Logger LOGGER = LogManager.getLogger();

    @SneakyThrows
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(SERVER_ADDRESS, PORT));

            while (!socketChannel.finishConnect()) {
                LOGGER.info("Client is trying to connect to server");
            }

            communicate(socketChannel);
        }
    }

    @SneakyThrows
    private static void communicate(SocketChannel socketChannel) {
        try (Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Client connected to server.");

            while (true) {
                LOGGER.info("Ваня: ");
                String message = scanner.nextLine();

                if ("exit".equalsIgnoreCase(message)) {
                    LOGGER.info("Client disconnected to server.");
                    break;
                }

                ByteBuffer buffer = ByteBuffer.wrap((message + "\n").getBytes(StandardCharsets.UTF_16));
                while (buffer.hasRemaining()) {
                    socketChannel.write(buffer);
                }
                buffer.clear();

                String answer = readMessage(buffer, socketChannel);
                LOGGER.info("Сервер: " + answer);
            }
        }
    }
}
