package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopiedFileHandler {
    private CopiedFileHandler() {
    }

    public static void cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            return;
        }

        String[] nameAndExtension = getNameAndExtension(path);
        Path directoryPath = path.getParent();
        String newFileName = nameAndExtension[0] + " - копия" + nameAndExtension[1];
        if (!Files.exists(directoryPath.resolve(newFileName))) {
            Files.createFile(directoryPath.resolve(newFileName));
        } else {
            int countOfCopies = 1;
            do {
                newFileName = "%s%s%s".formatted(
                    nameAndExtension[0],
                    String.format(" - копия (%d)", ++countOfCopies),
                    nameAndExtension[1]
                );
            } while (Files.exists(directoryPath.resolve(newFileName)));
            Files.createFile(directoryPath.resolve(newFileName));
        }
    }

    private static String[] getNameAndExtension(Path path) {
        String fileNameWithExtension = String.valueOf(path.getFileName());
        String extension;
        String name;
        int dotIndex = fileNameWithExtension.lastIndexOf(".");
        if (dotIndex != -1) {
            name = fileNameWithExtension.substring(0, dotIndex);
            extension = fileNameWithExtension.substring(dotIndex);
        } else {
            name = fileNameWithExtension;
            extension = "";
        }
        return new String[] {name, extension};
    }
}
