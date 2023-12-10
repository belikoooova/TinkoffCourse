package edu.hw10.task2;

import java.lang.reflect.Proxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheProxy {
    public static <T> T create(Class<T> tClass, T target) {
        return (T) Proxy.newProxyInstance(
            tClass.getClassLoader(),
            new Class<?>[] {tClass},
            new CacheInvocationHandler(target)
        );
    }
}
