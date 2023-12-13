package edu.hw11.task2;

import java.lang.reflect.Modifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ByteBuddyMethodEditor {
    public static Class<?> getMultiplierClassInsteadOfSum() {
        Class<?> res = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .defineMethod("sum", int.class, Modifier.STATIC | Modifier.PUBLIC)
            .withParameters(int.class, int.class)
            .intercept(MethodDelegation.to(MultiplicationInterceptor.class))
            .make()
            .load(ByteBuddyMethodEditor.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        return res;
    }
}
