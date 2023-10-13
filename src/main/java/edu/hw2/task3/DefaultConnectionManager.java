package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Random RANDOM = new Random(1);
    private static final int MOD = 5;

    @Override
    public Connection getConnection() {
        if (RANDOM.nextInt() % MOD == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
