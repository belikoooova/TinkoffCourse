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
        return animals.stream().sorted(new HeightComparator()).toList();
    }

    // Task 2.
    public static List<Animal> sortByWeightDescAndTakeSeveralElements(List<Animal> animals, int count) {
        return animals.stream().sorted(new WeightComparator()).limit(count).toList();
    }

    // Task 3.
    public static Map<Animal.Type, Integer> countTypes(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(
            Animal::type,
            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
        ));
    }

    // Task 1.
    static class HeightComparator implements Comparator<Animal> {
        @Override
        public int compare(Animal o1, Animal o2) {
            return o1.height() - o2.height();
        }
    }

    // Task 2.
    static class WeightComparator implements Comparator<Animal> {
        @Override
        public int compare(Animal o1, Animal o2) {
            return o2.weight() - o1.weight();
        }
    }
}
