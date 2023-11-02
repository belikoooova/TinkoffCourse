package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class ValidationError {
    private Category type;

    public Category getType() {
        return type;
    }

    public ValidationError(Category type) {
        this.type = type;
    }

    enum Category {
        NEGATIVE_AGE("Negative age"),
        NEGATIVE_WEIGHT("Negative weight"),
        NEGATIVE_HEIGHT("Negative height");

        String name;

        Category(String name) {
            this.name = name;
        }
    }

    public static Set<ValidationError> getErrorsSet(Animal animal) {
        Set<ValidationError> errorSet = new HashSet<>();
        if (animal.age() < 0) {
            errorSet.add(new ValidationError(Category.NEGATIVE_AGE));
        }
        if (animal.weight() < 0) {
            errorSet.add(new ValidationError(Category.NEGATIVE_WEIGHT));
        }
        if (animal.height() < 0) {
            errorSet.add(new ValidationError(Category.NEGATIVE_HEIGHT));
        }
        return errorSet;
    }

    @Override
    public String toString() {
        return type.name;
    }
}
