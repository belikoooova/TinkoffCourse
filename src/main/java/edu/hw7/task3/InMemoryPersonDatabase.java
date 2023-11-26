package edu.hw7.task3;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> idMap = new HashMap<>();
    private final Map<String, Set<Integer>> nameMap = new HashMap<>();
    private final Map<String, Set<Integer>> addressMap = new HashMap<>();
    private final Map<String, Set<Integer>> phoneMap = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        if (idMap.containsKey(person.id())) {
            throw new DuplicatedPersonIdException(person.id());
        }

        idMap.put(person.id(), person);
        nameMap.computeIfAbsent(person.name(), k -> new HashSet<>()).add(person.id());
        addressMap.computeIfAbsent(person.address(), k -> new HashSet<>()).add(person.id());
        phoneMap.computeIfAbsent(person.phoneNumber(), k -> new HashSet<>()).add(person.id());
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idMap.remove(id);
        if (person != null) {
            nameMap.getOrDefault(person.name(), Collections.emptySet()).remove(id);
            addressMap.getOrDefault(person.address(), Collections.emptySet()).remove(id);
            phoneMap.getOrDefault(person.phoneNumber(), Collections.emptySet()).remove(id);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return findByAttribute(nameMap, name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return findByAttribute(addressMap, address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return findByAttribute(phoneMap, phone);
    }

    private List<Person> findByAttribute(Map<String, Set<Integer>> map, String attribute) {
        return map
            .getOrDefault(attribute, Collections.emptySet())
            .stream()
            .map(idMap::get)
            .toList();
    }
}
