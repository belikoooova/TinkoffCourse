package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FileOutputsComposer {
    @Getter Checksum checksum;
    String fileName;

    public FileOutputsComposer(String fileName) {
        this.fileName = fileName;
    }

    public void write() throws IOException {
        Path path = Path.of(System.getProperty("user.dir")).resolve("HW6Task4File").resolve(fileName);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        File file =
            Files.createFile(path).toFile();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            writeWithOutputStream(outputStream);
        }
    }

    private void writeWithOutputStream(OutputStream outputStream) throws IOException {
        try (CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32())) {
            checksum = checkedOutputStream.getChecksum();
            writeWithCheckedOutputStream(checkedOutputStream);
        }
    }

    private void writeWithCheckedOutputStream(CheckedOutputStream checkedOutputStream) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream)) {
            writeWithBufferedOutputStream(bufferedOutputStream);
        }
    }

    private void writeWithBufferedOutputStream(BufferedOutputStream bufferedOutputStream) throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
            bufferedOutputStream,
            StandardCharsets.UTF_8
        )) {
            try (PrintWriter printWriter = new PrintWriter(outputStreamWriter, true)) {
                printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
            }
        }
    }
}
