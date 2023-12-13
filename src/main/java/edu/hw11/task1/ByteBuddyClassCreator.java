package edu.hw11.task1;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyClassCreator {
    @SneakyThrows
    public String createUnloadedTypeAndCallToString() {
        DynamicType.Unloaded unloadedType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make();
        Class<?> dynamicType = unloadedType.load(getClass().getClassLoader())
            .getLoaded();
        return dynamicType.newInstance().toString();
    }
}
