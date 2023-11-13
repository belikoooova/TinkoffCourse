package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static int counter = 0;
    private static final String IO_ERROR_MESSAGE = "Ошибка ввода-вывода";
    private Path path;

    public DiskMap() throws IOException {
        createDirectory();
    }

    @Override
    public int size() {
        return keySet().size();
    }

    @Override
    public boolean isEmpty() {
        return keySet().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (var curKey : keySet()) {
            if (curKey.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (var curKey : keySet()) {
            if (get(curKey).equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String get(Object key) {
        Path filePath = path.resolve(key.toString());
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            value = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            value = null;
        } catch (IOException e) {
            throw new RuntimeException(IO_ERROR_MESSAGE, e);
        }
        return value;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        Path filePath = path.resolve(key);
        String previousValue = remove(key);
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(IO_ERROR_MESSAGE, e);
        }
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(String.valueOf(filePath)))) {
            printWriter.println(value);
        } catch (IOException e) {
            throw new RuntimeException(IO_ERROR_MESSAGE, e);
        }
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        Path filePath = path.resolve(key.toString());
        String previousValue = get(key);
        if (containsKey(key)) {
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                throw new RuntimeException(IO_ERROR_MESSAGE, e);
            }
        }
        return previousValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var key : m.keySet()) {
            put(key, m.get(key));
        }
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            for (var key : keySet()) {
                remove(key);
                break;
            }
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        File[] keys = new File(String.valueOf(path)).listFiles();
        for (File fileKey : keys) {
            set.add(fileKey.getName());
        }
        return set;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Collection<String> mapValues = new ArrayList<>();
        for (var curKey : keySet()) {
            mapValues.add(get(curKey));
        }
        return mapValues;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> set = new HashSet<>();
        for (var curKey : keySet()) {
            set.add(new AbstractMap.SimpleEntry<>(curKey, get(curKey)));
        }
        return set;
    }

    private void createDirectory() throws IOException {
        String directoryName = "DiskMap" + ++counter;
        path = Path.of(System.getProperty("user.dir")).resolve(directoryName);
        if (Files.exists(path)) {
            File[] keys = new File(String.valueOf(path)).listFiles();
            for (File fileKey : keys) {
                Files.delete(fileKey.toPath());
            }
            Files.delete(path);
        }
        Files.createDirectory(path);
    }
}
