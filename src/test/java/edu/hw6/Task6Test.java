package edu.hw6;

import edu.hw6.task6.PortScanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task6Test {
    private static final int SQL_PORT = 5432;

    @Test
    @DisplayName("Test free SQL port")
    void testOnePort() throws IOException {
        // given
        int sqlPort = SQL_PORT;

        // when and then
        assertTrue(PortScanner.isPortFree(sqlPort));
    }

    @Test
    @DisplayName("Test ports with or without info")
    void testInfoPorts() throws IOException {
        // given and when and then
        PortScanner.scanPorts();
    }
}
