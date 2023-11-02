package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task5Test {
    private static final int CAT_AGE = 4;
    private static final int CAT_HEIGHT = 25;
    private static final int CAT_WEIGHT = 3;
    private static final int ANOTHER_CAT_AGE = 8;
    private static final int ANOTHER_CAT_HEIGHT = 28;
    private static final int ANOTHER_CAT_WEIGHT = 4;
    private static final int DOG_AGE = 7;
    private static final int DOG_HEIGHT = 55;
    private static final int DOG_WEIGHT = 10;
    private static final int BIRD_AGE = 1;
    private static final int BIRD_HEIGHT = 13;
    private static final int BIRD_WEIGHT = 1;
    private static final int FISH_AGE = 2;
    private static final int FISH_HEIGHT = 5;
    private static final int FISH_WEIGHT = 1;

    @Test
    @DisplayName("Find most popular sex")
    void findMostPopularSex() {
        // given
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, CAT_AGE, CAT_HEIGHT, CAT_WEIGHT, true));
        animals.add(new Animal("Alex", Animal.Type.DOG, Animal.Sex.F, DOG_AGE, DOG_HEIGHT, DOG_WEIGHT, false));
        animals.add(new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, BIRD_AGE, BIRD_HEIGHT, BIRD_WEIGHT, false));
        animals.add(new Animal("Sam", Animal.Type.FISH, Animal.Sex.F, FISH_AGE, FISH_HEIGHT, FISH_WEIGHT, false));
        animals.add(new Animal(
            "Pushok",
            Animal.Type.CAT,
            Animal.Sex.M,
            ANOTHER_CAT_AGE,
            ANOTHER_CAT_HEIGHT,
            ANOTHER_CAT_WEIGHT,
            true
        ));

        // when
        var result = AnimalUtilities.getMostPopularSex(animals);

        // then
        assertThat(result).isEqualTo(Animal.Sex.M);
    }
}
