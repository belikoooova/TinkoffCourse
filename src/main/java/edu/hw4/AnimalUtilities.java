package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalUtilities {
    private static final int BIG_HEIGHT = 100;
    private static final int WORDS_AMOUNT = 2;

    private AnimalUtilities() {
    }

    // Task 1.
    public static List<Animal> sortByHeightAsc(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    // Task 2.
    public static List<Animal> sortByWeightDescAndTakeSeveralElements(List<Animal> animals, int count) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(count).toList();
    }

    // Task 3.
    public static Map<Animal.Type, Integer> countTypes(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(
            Animal::type,
            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
        ));
    }

    // Task 4.
    public static Animal getAnimalWithMaxNameLength(List<Animal> animals) {
        return animals.stream().max((Comparator.comparingInt(o -> o.name().length()))).get();
    }

    // Task 5.
    public static Animal.Sex getMostPopularSex(List<Animal> animals) {
        var map = animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        return (map.getOrDefault(Animal.Sex.M, 0L) > map.getOrDefault(Animal.Sex.F, 0L)) ? Animal.Sex.M : Animal.Sex.F;
    }

    // Task 6.
    public static Map<Animal.Type, Animal> getHeaviestAnimals(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(
            Animal::type,
            Collectors.collectingAndThen(Collectors.maxBy((Comparator.comparingInt(Animal::weight))), Optional::get)
        ));
    }

    // Task 7.
    public static Animal getKthOldAnimal(List<Animal> animals, int k) {
        return animals.stream().sorted((o1, o2) -> o2.age() - o1.age()).skip(k - 1).findFirst().orElseThrow();
    }

    // Task 8.
    public static Optional<Animal> getHeaviestAnimalWithHeightLessThanK(List<Animal> animals, int k) {
        return animals.stream().filter(a -> a.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    // Task 9.
    public static Integer getAmountOfPaws(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    // Task 10.
    public static List<Animal> getAnimalsWithAgeNotEqualPaws(List<Animal> animals) {
        return animals.stream().filter(a -> a.paws() != a.age()).toList();
    }

    // Task 11.
    public static List<Animal> getBitingAndBigAnimals(List<Animal> animals) {
        return animals.stream().filter(a -> a.bites() && a.height() > BIG_HEIGHT).toList();
    }

    // Task 12.
    public static Integer getAmountOfAnimalsWithWeightGreaterThanHeight(List<Animal> animals) {
        return (int) animals.stream().filter(a -> a.weight() > a.height()).count();
    }

    // Task 13.
    public static Integer getAmountOfAnimalsWithNameThatContainsMoreThanTwoWords(List<Animal> animals) {
        return (int) animals.stream().filter(a -> a.name().split(" ").length > WORDS_AMOUNT).count();
    }

    // Task 14.
    public static Boolean isThereDogWithHeightGreaterThanK(List<Animal> animals, int k) {
        return animals.stream().anyMatch(a -> a.type().equals(Animal.Type.DOG) && a.height() > k);
    }

    // Task 15.
    public static Integer findTotalWeightOfAnimalsWithHeightFromKToL(List<Animal> animals, int k, int l) {
        return animals.stream().filter(a -> a.height() >= k && a.height() <= l).mapToInt(Animal::weight).sum();
    }

    // Task 16.
    public static List<Animal> specialSort(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    // Task 17.
    public static Boolean areSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();
        long dogCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .count();
        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();
        long spiderCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .count();
        if (dogCount == 0 || spiderCount == 0) {
            return false;
        }
        return (double)dogBites / dogCount < (double)spiderBites / spiderCount;
    }
}
