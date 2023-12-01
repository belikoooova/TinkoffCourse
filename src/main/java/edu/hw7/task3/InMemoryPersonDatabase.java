package edu.hw7.task3;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, Set<Integer>> personIdsByName = new HashMap<>();
    private final Map<String, Set<Integer>> personIdsByAddress = new HashMap<>();
    private final Map<String, Set<Integer>> personIdsByPhones = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        if (personsById.containsKey(person.id())) {
            throw new DuplicatedPersonIdException(person.id());
        }

        personsById.put(person.id(), person);
        personIdsByName.computeIfAbsent(person.name(), k -> new HashSet<>()).add(person.id());
        personIdsByAddress.computeIfAbsent(person.address(), k -> new HashSet<>()).add(person.id());
        personIdsByPhones.computeIfAbsent(person.phoneNumber(), k -> new HashSet<>()).add(person.id());
    }

    @Override
    public synchronized void delete(int id) {
        Person person = personsById.remove(id);
        if (person != null) {
            personIdsByName.getOrDefault(person.name(), Collections.emptySet()).remove(id);
            personIdsByAddress.getOrDefault(person.address(), Collections.emptySet()).remove(id);
            personIdsByPhones.getOrDefault(person.phoneNumber(), Collections.emptySet()).remove(id);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return findByAttribute(personIdsByName, name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return findByAttribute(personIdsByAddress, address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return findByAttribute(personIdsByPhones, phone);
    }

    private List<Person> findByAttribute(Map<String, Set<Integer>> map, String attribute) {
        return map
            .getOrDefault(attribute, Collections.emptySet())
            .stream()
            .map(personsById::get)
            .toList();
    }
}
