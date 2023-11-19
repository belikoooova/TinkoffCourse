package edu.project3.writers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("MagicNumber")
public class WriterUtils {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    public static Map<Integer, String> getHttpStatusDescriptions() {
        Map<Integer, String> httpStatusDescriptions = new HashMap<>();

        httpStatusDescriptions.put(100, "Continue");
        httpStatusDescriptions.put(101, "Switching Protocols");
        httpStatusDescriptions.put(102, "Processing");

        httpStatusDescriptions.put(200, "OK");
        httpStatusDescriptions.put(201, "Created");
        httpStatusDescriptions.put(202, "Accepted");
        httpStatusDescriptions.put(203, "Non-Authoritative Information");
        httpStatusDescriptions.put(204, "No Content");
        httpStatusDescriptions.put(205, "Reset Content");
        httpStatusDescriptions.put(206, "Partial Content");
        httpStatusDescriptions.put(207, "Multi-Status");
        httpStatusDescriptions.put(208, "Already Reported");
        httpStatusDescriptions.put(226, "IM Used");

        httpStatusDescriptions.put(300, "Multiple Choices");
        httpStatusDescriptions.put(301, "Moved Permanently");
        httpStatusDescriptions.put(302, "Found");
        httpStatusDescriptions.put(303, "See Other");
        httpStatusDescriptions.put(304, "Not Modified");
        httpStatusDescriptions.put(305, "Use Proxy");
        httpStatusDescriptions.put(307, "Temporary Redirect");
        httpStatusDescriptions.put(308, "Permanent Redirect");

        httpStatusDescriptions.put(400, "Bad Request");
        httpStatusDescriptions.put(401, "Unauthorized");
        httpStatusDescriptions.put(402, "Payment Required");
        httpStatusDescriptions.put(403, "Forbidden");
        httpStatusDescriptions.put(404, "Not Found");
        httpStatusDescriptions.put(405, "Method Not Allowed");
        httpStatusDescriptions.put(406, "Not Acceptable");
        httpStatusDescriptions.put(407, "Proxy Authentication Required");
        httpStatusDescriptions.put(408, "Request Timeout");
        httpStatusDescriptions.put(409, "Conflict");
        httpStatusDescriptions.put(410, "Gone");
        httpStatusDescriptions.put(411, "Length Required");
        httpStatusDescriptions.put(412, "Precondition Failed");
        httpStatusDescriptions.put(413, "Payload Too Large");
        httpStatusDescriptions.put(414, "URI Too Long");
        httpStatusDescriptions.put(415, "Unsupported Media Type");
        httpStatusDescriptions.put(416, "Range Not Satisfiable");
        httpStatusDescriptions.put(417, "Expectation Failed");
        httpStatusDescriptions.put(421, "Misdirected Request");
        httpStatusDescriptions.put(422, "Unprocessable Entity");
        httpStatusDescriptions.put(423, "Locked");
        httpStatusDescriptions.put(424, "Failed Dependency");
        httpStatusDescriptions.put(426, "Upgrade Required");
        httpStatusDescriptions.put(428, "Precondition Required");
        httpStatusDescriptions.put(429, "Too Many Requests");
        httpStatusDescriptions.put(431, "Request Header Fields Too Large");
        httpStatusDescriptions.put(451, "Unavailable For Legal Reasons");

        httpStatusDescriptions.put(500, "Internal Server Error");
        httpStatusDescriptions.put(501, "Not Implemented");
        httpStatusDescriptions.put(502, "Bad Gateway");
        httpStatusDescriptions.put(503, "Service Unavailable");
        httpStatusDescriptions.put(504, "Gateway Timeout");
        httpStatusDescriptions.put(505, "HTTP Version Not Supported");
        httpStatusDescriptions.put(506, "Variant Also Negotiates");
        httpStatusDescriptions.put(507, "Insufficient Storage");
        httpStatusDescriptions.put(508, "Loop Detected");
        httpStatusDescriptions.put(510, "Not Extended");
        httpStatusDescriptions.put(511, "Network Authentication Required");

        return httpStatusDescriptions;
    }

    public static void deleteIfExist(String path) {
        if (Files.exists(Path.of(path))) {
            try {
                Files.delete(Path.of(path));
            } catch (IOException e) {
                throw new RuntimeException("Error while deleting existing file.", e);
            }
        }
    }
}
