package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Random;
import lombok.SneakyThrows;

public class RandomObjectGenerator {
    private static final Random RANDOM = new Random();
    private static final int CHARS_AMOUNT = 65536;

    @SneakyThrows
    public <T> T nextObject(Class<T> tClass, String... factoryMethodName) {
        if (tClass.isRecord()) {
            return getRandomRecord(tClass);
        }

        T classObject = getEmptyObject(tClass, factoryMethodName);

        for (Field field : tClass.getDeclaredFields()) {
            if (field.getType().isPrimitive()) {
                field.set(
                    classObject,
                    getRandomPrimitiveValue(field.getType(), getMinFromField(field), getMaxFromField(field))
                );
            } else {
                field.set(
                    classObject,
                    field.isAnnotationPresent(NotNull.class) ? field.getType().getConstructor().newInstance() : null
                );
            }
        }

        return classObject;
    }

    @SneakyThrows
    private <T> T getRandomRecord(Class<T> tClass) {
        Class[] components = new Class[tClass.getRecordComponents().length];
        int index = 0;
        for (var component : tClass.getRecordComponents()) {
            components[index++] = component.getType();
        }

        Constructor<T> constructor = tClass.getDeclaredConstructor(components);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }

        Object[] parameters = new Object[tClass.getRecordComponents().length];
        index = 0;
        for (var component : tClass.getRecordComponents()) {
            if (component.getType().isPrimitive()) {
                parameters[index++] = getRandomPrimitiveValue(
                    component.getType(),
                    getMinFromComponent(component),
                    getMaxFromComponent(component)
                );
            } else {
                parameters[index++] =
                    component.isAnnotationPresent(NotNull.class)
                        ? component.getType().getConstructor().newInstance()
                        : null;
            }
        }

        return constructor.newInstance(parameters);
    }

    @SneakyThrows
    private <T> T getEmptyObject(Class<T> tClass, String... factoryMethodName) {
        T classObject;
        if (factoryMethodName.length != 0) {
            Method factoryMethod = tClass.getDeclaredMethod(factoryMethodName[0]);
            if (!factoryMethod.isAccessible()) {
                factoryMethod.setAccessible(true);
            }

            if (!factoryMethod.getReturnType().equals(tClass)
                || !java.lang.reflect.Modifier.isStatic(factoryMethod.getModifiers())) {
                throw new IllegalArgumentException("Factory method is not valid.");
            }
            classObject = (T) factoryMethod.invoke(null);
        } else {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            classObject = constructor.newInstance();
        }
        return classObject;
    }

    private int getMinFromComponent(RecordComponent component) {
        return component.isAnnotationPresent(Min.class) ? component.getAnnotation(Min.class).value()
            : Integer.MIN_VALUE;
    }

    private int getMinFromField(Field field) {
        return field.isAnnotationPresent(Min.class) ? field.getAnnotation(Min.class).value() : Integer.MIN_VALUE;
    }

    private int getMaxFromComponent(RecordComponent component) {
        return component.isAnnotationPresent(Max.class) ? component.getAnnotation(Max.class).value()
            : Integer.MAX_VALUE;
    }

    private int getMaxFromField(Field field) {
        return field.isAnnotationPresent(Max.class) ? field.getAnnotation(Max.class).value() : Integer.MAX_VALUE;
    }

    private <T> T getRandomPrimitiveValue(Class<T> type, int min, int max) {
        return (T) switch (type.getName()) {
            case "int" -> Integer.valueOf(RANDOM.nextInt(min, max));
            case "double" -> Double.valueOf(RANDOM.nextInt(min, max) + RANDOM.nextDouble());
            case "long" -> Long.valueOf(RANDOM.nextLong(min, max));
            case "float" -> Float.valueOf(RANDOM.nextInt(min, max) + RANDOM.nextFloat());
            case "boolean" -> Boolean.valueOf(RANDOM.nextBoolean());
            case "byte" -> Byte.valueOf((byte) RANDOM.nextInt(min, max));
            case "short" -> Short.valueOf((short) RANDOM.nextInt(min, max));
            case "char" -> Character.valueOf((char) RANDOM.nextInt(CHARS_AMOUNT));
            default -> throw new IllegalArgumentException("Type must be primitive.");
        };
    }
}
