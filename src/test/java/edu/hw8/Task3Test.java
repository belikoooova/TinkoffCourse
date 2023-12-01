package edu.hw8;

import edu.hw8.task3.Hacker;
import edu.hw8.task3.HackerUtils;
import edu.hw8.task3.MultiThreadHacker;
import edu.hw8.task3.SingleThreadHacker;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {
    @Test
    public void testGetMappedInfo() {
        // given
        List<String> input = Arrays.asList("user1 hash1", "user2 hash2");
        Map<String, String> expected = new HashMap<>();
        expected.put("hash1", "user1");
        expected.put("hash2", "user2");

        // when
        var hashesWithUsers = HackerUtils.getMappedInfo(input);

        // then
        assertEquals(expected, hashesWithUsers);
    }

    @Test
    void testGetMappedInfoWithIncorrectInput() {
        // given
        List<String> input = Arrays.asList("user1", "user2 hash2");

        // when and then
        assertThrows(IllegalArgumentException.class, () -> HackerUtils.getMappedInfo(input));
    }

    @Test
    void testSingleThreadHacker() {
        // given
        var expected = getPasswordsByUser();
        var given = getUsersWithHashes(expected);
        Hacker hacker = new SingleThreadHacker();

        // when
        var passwordByUsers = hacker.getHackedUsersWithPasswords(given);

        // then
        assertEquals(expected, passwordByUsers);
    }

    @Test
    void testMultiThreadHacker() {
        // given
        var expected = getPasswordsByUser();
        var given = getUsersWithHashes(expected);
        Hacker hacker = new MultiThreadHacker();

        // when
        var passwordByUsers = hacker.getHackedUsersWithPasswords(given);

        // then
        assertEquals(expected, passwordByUsers);
    }

    private static Map<String, String> getPasswordsByUser() {
        Map<String, String> passwordsByUser = new HashMap<>();
        passwordsByUser.put("user1", "A9me");
        passwordsByUser.put("user2", "hOla");
        passwordsByUser.put("user3", "kI9");
        passwordsByUser.put("user4", "R");
        passwordsByUser.put("user5", "73k4");

        return passwordsByUser;
    }

    private static List<String> getUsersWithHashes(Map<String, String> passwordsByUser) {
        List<String> usersWithHashes = new ArrayList<>();
        for (var passwordByUser : passwordsByUser.entrySet()) {
            String hash = DigestUtils.md5Hex(passwordByUser.getValue());
            usersWithHashes.add("%s %s".formatted(passwordByUser.getKey(), hash));
        }
        return usersWithHashes;
    }
}
