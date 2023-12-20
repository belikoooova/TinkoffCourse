package edu.hw10.task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

public class CacheInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Path directory;
    private final Map<String, Object> cacheMap = new HashMap<>();

    @SneakyThrows
    public CacheInvocationHandler(Object target) {
        this.target = target;
        directory = Files.createTempDirectory("cache");
    }

    @SneakyThrows
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(target, args);
        }
        String key = "%s_%s".formatted(method.getName(), Arrays.deepToString(args));
        if (method.getAnnotation(Cache.class).persist()) {
            Path file = Paths.get(directory.toString(), key);
            if (Files.exists(file)) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toString()))) {
                    return ois.readObject();
                }
            } else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toString()))) {
                    Object result = method.invoke(target, args);
                    oos.writeObject(result);
                }
            }
        } else {
            cacheMap.computeIfAbsent(key, k -> {
                try {
                    return method.invoke(target, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
            return cacheMap.get(key);
        }
        return method.invoke(target, args);
    }
}
