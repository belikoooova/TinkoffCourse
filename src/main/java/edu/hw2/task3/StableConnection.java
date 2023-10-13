package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info(String.format("The command [%s] is executed%n", command));
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Now the connection is closed\n");
    }
}
