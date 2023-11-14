package edu.hw6;

import edu.hw6.task4.FileOutputsComposer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    @DisplayName("Test write")
    void testWrite() throws IOException {
        // given
        String fileName = "Test";

        // when
        FileOutputsComposer fileOutputsComposer = new FileOutputsComposer(fileName);
        fileOutputsComposer.write();

        // then
        File file = Path.of(System.getProperty("user.dir")).resolve("HW6Task4File").resolve(fileName).toFile();
        try (InputStream inputStream = new FileInputStream(file)) {
            try (CheckedInputStream checkedOutputStream = new CheckedInputStream(inputStream, new Adler32())) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(checkedOutputStream))) {
                    assertThat(bufferedReader.readLine()).isEqualTo(
                        "Programming is learned by writing programs. ― Brian Kernighan");
                }
            }
        }
    }

    @Test
    @DisplayName("Test checksum")
    void testChecksum() throws IOException {
        // given
        String fileName = "Test";

        // when
        FileOutputsComposer fileOutputsComposer = new FileOutputsComposer(fileName);
        fileOutputsComposer.write();

        // then
        File file = Path.of(System.getProperty("user.dir")).resolve("HW6Task4File").resolve(fileName).toFile();
        try (InputStream inputStream = new FileInputStream(file)) {
            try (CheckedInputStream checkedOutputStream = new CheckedInputStream(inputStream, new Adler32())) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(checkedOutputStream))) {
                    bufferedReader.readLine();
                }
                assertThat(fileOutputsComposer.getChecksum().getValue()).isEqualTo(checkedOutputStream.getChecksum()
                    .getValue());
            }
        }
    }
}
