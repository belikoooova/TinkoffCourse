package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimalUtilities {
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
}
