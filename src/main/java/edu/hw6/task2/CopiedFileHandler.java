package edu.hw6.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CopiedFileHandler {
    private static final int MAX_ATTEMPTS = 10;

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
            int maxNumber = findMaxNumberOfCopies(directoryPath, nameAndExtension);
            ++maxNumber;
            for (int i = 0; i < MAX_ATTEMPTS; ++i) {
                try {
                    newFileName = "%s%s%s".formatted(
                        nameAndExtension[0],
                        String.format(" - копия (%d)", maxNumber),
                        nameAndExtension[1]
                    );
                    Files.createFile(directoryPath.resolve(newFileName));
                } catch (FileAlreadyExistsException exception) {
                    ++maxNumber;
                }
            }
        }
    }

    private static int findMaxNumberOfCopies(Path directoryPath, String[] nameAndExtension) {
        var files = Arrays.stream(new File(String.valueOf(directoryPath)).listFiles()).toList();
        Pattern pattern = Pattern.compile(nameAndExtension[0] + " - копия\\s?\\(?(\\d*)\\)?" + nameAndExtension[1]);
        int maxNumber = 1;
        for (var file : files) {
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.find()) {
                if (matcher.group(1).isEmpty()) {
                    continue;
                }
                maxNumber = Math.max(maxNumber, Integer.parseInt(matcher.group(1)));
            }
        }
        return maxNumber;
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
