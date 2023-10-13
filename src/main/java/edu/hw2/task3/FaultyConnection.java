package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final int MOD = 2;
    private static final int SEED = 22;
    private static final Random RANDOM = new Random(SEED);

    @Override
    public void execute(String command) {
        if (RANDOM.nextInt() % MOD == 0) {
            throw new ConnectionException();
        }
        LOGGER.info(String.format("The command [%s] is executed%n", command));
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Now the connection is closed\n");
    }
}
