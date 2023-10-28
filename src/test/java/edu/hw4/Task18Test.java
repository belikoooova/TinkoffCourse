package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task18Test {
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
    private static final int FISH_AGE_3 = 1;
    private static final int FISH_HEIGHT_3 = 13;
    private static final int FISH_WEIGHT_3 = 2;
    private static final int FISH_AGE_2 = 2;
    private static final int FISH_HEIGHT_2 = 14;
    private static final int FISH_WEIGHT_2 = 1;
    private static final int FISH_AGE_1 = 2;
    private static final int FISH_HEIGHT_1 = 5;
    private static final int FISH_WEIGHT_1 = 1;
    private static final int FISH_AGE_4 = 3;
    private static final int FISH_HEIGHT_4 = 9;
    private static final int FISH_WEIGHT_4 = 11;

    @Test
    @DisplayName("Find the heaviest fish")
    void findTheHeaviestFish() {
        // given
        var animals = new ArrayList<List<Animal>>();
        animals.add(getFirstListOfAnimals());
        animals.add(getSecondListOfAnimals());

        // when
        var result = AnimalUtilities.getTheHeaviestFish(animals);

        // then
        assertThat(result).isEqualTo(naiveCount(animals));
    }

    static Animal naiveCount(List<List<Animal>> animalsLists) {
        Animal result = null;
        for (var list : animalsLists) {
            for (var animal : list) {
                if (!animal.type().equals(Animal.Type.FISH)) {
                    continue;
                }
                result = result == null || result.weight() < animal.weight() ? animal : result;
            }
        }
        return result;
    }

    private List<Animal> getFirstListOfAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, CAT_AGE, CAT_HEIGHT, CAT_WEIGHT, true));
        animals.add(new Animal("Alex", Animal.Type.DOG, Animal.Sex.F, DOG_AGE, DOG_HEIGHT, DOG_WEIGHT, false));
        animals.add(new Animal(
            "Kesha",
            Animal.Type.FISH,
            Animal.Sex.M,
            FISH_AGE_3,
            FISH_HEIGHT_3,
            FISH_WEIGHT_3,
            false
        ));
        animals.add(new Animal(
            "Sam With Big Brain",
            Animal.Type.FISH,
            Animal.Sex.F,
            FISH_AGE_2,
            FISH_HEIGHT_2,
            FISH_WEIGHT_2,
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
            FISH_AGE_1,
            FISH_HEIGHT_1,
            FISH_WEIGHT_1,
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

    private List<Animal> getSecondListOfAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, CAT_AGE, CAT_HEIGHT, CAT_WEIGHT, true));
        animals.add(new Animal("Pol", Animal.Type.DOG, Animal.Sex.F, DOG_AGE, DOG_HEIGHT, DOG_WEIGHT, false));
        animals.add(new Animal(
            "Nick",
            Animal.Type.FISH,
            Animal.Sex.M,
            FISH_AGE_4,
            FISH_HEIGHT_4,
            FISH_WEIGHT_4,
            false
        ));
        return animals;
    }
}
