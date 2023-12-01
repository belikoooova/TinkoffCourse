package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;
import static edu.hw8.task3.HackerUtils.CHARACTERS;
import static edu.hw8.task3.HackerUtils.ERROR_MESSAGE;
import static edu.hw8.task3.HackerUtils.MAX_PASSWORD_LENGTH;
import static edu.hw8.task3.HackerUtils.getMappedInfo;

@NoArgsConstructor
public class MultiThreadHacker implements Hacker {
    private static final int NUMBER_OF_THREADS = 2;
    private static final int TIMEOUT = 60;

    @SneakyThrows
    @Override
    public Map<String, String> getHackedUsersWithPasswords(List<String> usersWithHashes) {
        Map<String, String> usersByHashes = new ConcurrentHashMap<>(getMappedInfo(usersWithHashes));
        Map<String, String> passwordsByUsers = new ConcurrentHashMap<>();
        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int length = 1; length <= MAX_PASSWORD_LENGTH; ++length) {
            List<Segment> segments = getSegmentsForIndexes(length);
            for (var segment : segments) {
                final int finalLength = length;
                service.submit(() -> {
                    for (long index = segment.start(); index < segment.end(); ++index) {
                        String password = nextPassword(index, finalLength);
                        String hash = DigestUtils.md5Hex(password).toLowerCase();
                        if (usersByHashes.containsKey(hash)) {
                            passwordsByUsers.put(usersByHashes.get(hash), password);
                            usersByHashes.remove(hash);
                        }
                        if (usersByHashes.isEmpty()) {
                            break;
                        }
                    }
                });
            }
        }
        shutdownAndAwaitTermination(service);
        if (usersByHashes.isEmpty()) {
            return passwordsByUsers;
        }
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    private String nextPassword(long index, int length) {
        long copyOfIndex = index;
        StringBuffer password = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            password.append(CHARACTERS[(int) copyOfIndex % CHARACTERS.length]);
            copyOfIndex /= CHARACTERS.length;
        }
        return password.reverse().toString();
    }

    private List<Segment> getSegmentsForIndexes(int lengthOfPassword) {
        long passwordAmount = (long) Math.pow(CHARACTERS.length, lengthOfPassword);
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            segments.add(new Segment(
                passwordAmount / NUMBER_OF_THREADS * i,
                Math.min(passwordAmount / NUMBER_OF_THREADS * (i + 1), passwordAmount)
            ));
        }
        return segments;
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    throw new TimeoutException("Pool did not terminate");
                }
            }
        } catch (InterruptedException ex) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private record Segment(long start, long end) {
    }
}
