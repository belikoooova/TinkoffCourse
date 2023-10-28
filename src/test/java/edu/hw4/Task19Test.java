package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task19Test {
    private static final int CAT_AGE = 4;
    private static final int CAT_HEIGHT = 25;
    private static final int CAT_WEIGHT = 3;
    private static final int ANOTHER_CAT_AGE = -8;
    private static final int ANOTHER_CAT_HEIGHT = -28;
    private static final int ANOTHER_CAT_WEIGHT = 4;
    private static final int DOG_AGE = 7;
    private static final int DOG_HEIGHT = -55;
    private static final int DOG_WEIGHT = 10;
    private static final int BIRD_AGE = 1;
    private static final int BIRD_HEIGHT = 13;
    private static final int BIRD_WEIGHT = -2;

    @Test
    @DisplayName("Get error map")
    void getErrorMap() {
        // given
        var animals = getListOfAnimals();

        // when
        var result = AnimalUtilities.getErrorMap(animals);

        // then
        assertThat(result.get("Barsik").isEmpty()).isTrue();
        assertThat(result.get("Alex").isEmpty()).isFalse();
        assertThat(result.get("Alex").stream()
            .anyMatch(e -> e.getType().equals(ValidationError.Category.NEGATIVE_HEIGHT))).isTrue();
        assertThat(result.get("Kesha").isEmpty()).isFalse();
        assertThat(result.get("Kesha").stream()
            .anyMatch(e -> e.getType().equals(ValidationError.Category.NEGATIVE_WEIGHT))).isTrue();
        assertThat(result.get("Pushok").isEmpty()).isFalse();
        assertThat(result.get("Pushok").stream()
            .anyMatch(e -> e.getType().equals(ValidationError.Category.NEGATIVE_AGE))).isTrue();
        assertThat(result.get("Pushok").stream()
            .anyMatch(e -> e.getType().equals(ValidationError.Category.NEGATIVE_HEIGHT))).isTrue();
    }

    private List<Animal> getListOfAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, CAT_AGE, CAT_HEIGHT, CAT_WEIGHT, true));
        animals.add(new Animal("Alex", Animal.Type.DOG, Animal.Sex.F, DOG_AGE, DOG_HEIGHT, DOG_WEIGHT, false));
        animals.add(new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, BIRD_AGE, BIRD_HEIGHT, BIRD_WEIGHT, false));
        animals.add(new Animal(
            "Pushok",
            Animal.Type.CAT,
            Animal.Sex.M,
            ANOTHER_CAT_AGE,
            ANOTHER_CAT_HEIGHT,
            ANOTHER_CAT_WEIGHT,
            true
        ));
        return animals;
    }
}
