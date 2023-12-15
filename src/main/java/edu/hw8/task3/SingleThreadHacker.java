package edu.hw8.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import static edu.hw8.task3.HackerUtils.CHARACTERS;
import static edu.hw8.task3.HackerUtils.ERROR_MESSAGE;
import static edu.hw8.task3.HackerUtils.MAX_PASSWORD_LENGTH;
import static edu.hw8.task3.HackerUtils.getMappedInfo;

public class SingleThreadHacker implements Hacker {
    @Override
    public Map<String, String> getHackedUsersWithPasswords(List<String> usersWithHashes) {
        Map<String, String> loginsByHashes = getMappedInfo(usersWithHashes);
        Map<String, String> passwordsByLogins = new HashMap<>();
        for (int length = 1; length <= MAX_PASSWORD_LENGTH; ++length) {
            long passwordAmount = (long) Math.pow(CHARACTERS.length, length);
            for (int index = 0; index < passwordAmount; ++index) {
                String password = nextPassword(index, length);
                String hash = DigestUtils.md5Hex(password).toLowerCase();
                if (loginsByHashes.containsKey(hash)) {
                    passwordsByLogins.put(loginsByHashes.get(hash), password);
                    loginsByHashes.remove(hash);
                }
                if (loginsByHashes.isEmpty()) {
                    return passwordsByLogins;
                }
            }
        }
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    private String nextPassword(long index, int length) {
        long copyOfIndex = index;
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            password.append(CHARACTERS[(int) (copyOfIndex % CHARACTERS.length)]);
            copyOfIndex /= CHARACTERS.length;
        }
        return password.reverse().toString();
    }
}
