package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task15Test {
    private static final int CAT_AGE = 4;
    private static final int CAT_HEIGHT = 25;
    private static final int CAT_WEIGHT = 3;
    private static final int ANOTHER_CAT_AGE = 8;
    private static final int ANOTHER_CAT_HEIGHT = 28;
    private static final int ANOTHER_CAT_WEIGHT = 4;
    private static final int DOG_AGE = 7;
    private static final int DOG_HEIGHT = 55;
    private static final int DOG_WEIGHT = 10;
    private static final int BIG_DOG_AGE = 8;
    private static final int BIG_DOG_HEIGHT = 120;
    private static final int BIG_DOG_WEIGHT = 20;
    private static final int BIRD_AGE = 1;
    private static final int BIRD_HEIGHT = 13;
    private static final int BIRD_WEIGHT = 2;
    private static final int ANOTHER_BIRD_AGE = 2;
    private static final int ANOTHER_BIRD_HEIGHT = 14;
    private static final int ANOTHER_BIRD_WEIGHT = 1;
    private static final int FISH_AGE = 2;
    private static final int FISH_HEIGHT = 5;
    private static final int FISH_WEIGHT = 1;
    private static final int K = 3;
    private static final int L = 11;

    @Test
    @DisplayName("Find total weight of animals with height from K to L")
    void findTotalWeightOfAnimalsWithHeightFromKToL() {
        // given
        var animals = getListOfAnimals();

        // when
        var result = AnimalUtilities.findTotalWeightOfAnimalsWithHeightFromKToL(animals, K, L);

        // then
        assertThat(result).isEqualTo(naiveCount(animals));
    }

    private Integer naiveCount(List<Animal> animals) {
        int sum = 0;
        for (var animal : animals) {
            if (animal.height() >= K && animal.height() <= L) {
                sum += animal.weight();
            }
        }
        return sum;
    }

    private List<Animal> getListOfAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, CAT_AGE, CAT_HEIGHT, CAT_WEIGHT, true));
        animals.add(new Animal("Alex", Animal.Type.DOG, Animal.Sex.F, DOG_AGE, DOG_HEIGHT, DOG_WEIGHT, false));
        animals.add(new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, BIRD_AGE, BIRD_HEIGHT, BIRD_WEIGHT, false));
        animals.add(new Animal(
            "Sam With Big Brain",
            Animal.Type.FISH,
            Animal.Sex.F,
            FISH_AGE,
            FISH_HEIGHT,
            FISH_WEIGHT,
            false
        ));
        animals.add(new Animal(
            "Pushok My Love",
            Animal.Type.CAT,
            Animal.Sex.M,
            ANOTHER_CAT_AGE,
            ANOTHER_CAT_HEIGHT,
            ANOTHER_CAT_WEIGHT,
            true
        ));
        animals.add(new Animal(
            "Rob",
            Animal.Type.BIRD,
            Animal.Sex.M,
            ANOTHER_BIRD_AGE,
            ANOTHER_BIRD_HEIGHT,
            ANOTHER_BIRD_WEIGHT,
            false
        ));
        animals.add(new Animal(
            "Alice",
            Animal.Type.DOG,
            Animal.Sex.F,
            BIG_DOG_AGE,
            BIG_DOG_HEIGHT,
            BIG_DOG_WEIGHT,
            true
        ));
        return animals;
    }
}
