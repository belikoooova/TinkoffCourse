package edu.project3.readers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FileReader implements Reader {
    private Path path;

    @Override
    public List<String> read() {
        List<String> strings = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(String.valueOf(path));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (bufferedReader.ready()) {
                strings.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while reading a file by path %s.", path), e);
        }
        return strings;
    }
}
