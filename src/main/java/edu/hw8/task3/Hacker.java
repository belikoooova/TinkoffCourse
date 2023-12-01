package edu.hw8.task3;

import java.util.List;
import java.util.Map;

public interface Hacker {
    Map<String, String> getHackedUsersWithPasswords(List<String> usersWithHashes);
}
