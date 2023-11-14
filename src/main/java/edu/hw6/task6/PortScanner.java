package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class PortScanner {
    private PortScanner() {
    }

    private static final int MAX_PORT = 49151;
    private static final Logger LOGGER = LogManager.getLogger();

    public static void scanPorts() throws IOException {
        LOGGER.info("Port\tFree\tService");
        var infoMap = createKnownPortsMap();
        for (int i = 0; i < MAX_PORT; ++i) {
            String info = infoMap.getOrDefault(i, "");
            LOGGER.info(String.valueOf(i) + '\t' + isPortFree(i) + '\t' + info);
        }
    }

    public static boolean isPortFree(int port) throws IOException {
        if (port < 0 || port > MAX_PORT) {
            throw new IllegalArgumentException("Port number must be between 0 and 49151");
        }
        ServerSocket serverSocket = null;
        DatagramSocket datagramSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            datagramSocket = new DatagramSocket(port);
            return true;
        } catch (IOException ex) {
            return false;
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    private static Map<Integer, String> createKnownPortsMap() {
        Map<Integer, String> knownPorts = new HashMap<>();
        knownPorts.put(21, "FTP - File Transfer Protocol");
        knownPorts.put(22, "SSH - Secure Shell");
        knownPorts.put(23, "Telnet");
        knownPorts.put(25, "SMTP - Simple Mail Transfer Protocol");
        knownPorts.put(53, "DNS - Domain Name System");
        knownPorts.put(80, "HTTP - Hypertext Transfer Protocol");
        knownPorts.put(110, "POP3 - Post Office Protocol");
        knownPorts.put(143, "IMAP - Internet Message Access Protocol");
        knownPorts.put(443, "HTTPS - HTTP Secure");
        knownPorts.put(993, "IMAP over SSL");
        knownPorts.put(995, "POP3 over SSL");
        knownPorts.put(5432, "PostgreSQL");

        return knownPorts;
    }
}
