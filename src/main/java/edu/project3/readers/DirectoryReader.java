package edu.project3.readers;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DirectoryReader implements Reader {
    private List<Path> paths;

    @Override
    public List<String> read() {
        List<String> strings = new ArrayList<>();
        for (Path path: paths) {
            // TODO: Maybe use setters?
            Reader fileReader = new FileReader(path);
            strings.addAll(fileReader.read());
        }
        return strings;
    }
}
