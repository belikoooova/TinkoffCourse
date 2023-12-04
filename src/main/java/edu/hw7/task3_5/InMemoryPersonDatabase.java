package edu.hw7.task3_5;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.SneakyThrows;

public class InMemoryPersonDatabase implements PersonDatabase {
    private static final int TIMEOUT = 60;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Map<Integer, Person> idMap = new HashMap<>();
    private final Map<String, Set<Integer>> nameMap = new HashMap<>();
    private final Map<String, Set<Integer>> addressMap = new HashMap<>();
    private final Map<String, Set<Integer>> phoneMap = new HashMap<>();

    @SneakyThrows
    @Override
    public void add(Person person) {
        boolean wasLocked = lock.writeLock().tryLock(TIMEOUT, TimeUnit.SECONDS);
        if (!wasLocked) {
            throw new TimeoutException("Timeout while adiing.");
        }
        try {
            if (idMap.containsKey(person.id())) {
                throw new DuplicatedPersonIdException(person.id());
            }

            idMap.put(person.id(), person);
            nameMap.computeIfAbsent(person.name(), k -> new HashSet<>()).add(person.id());
            addressMap.computeIfAbsent(person.address(), k -> new HashSet<>()).add(person.id());
            phoneMap.computeIfAbsent(person.phoneNumber(), k -> new HashSet<>()).add(person.id());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @SneakyThrows
    @Override
    public void delete(int id) {
        boolean wasLocked = lock.readLock().tryLock(TIMEOUT, TimeUnit.SECONDS);
        if (!wasLocked) {
            throw new TimeoutException("Timeout while deleting.");
        }
        try {
            Person person = idMap.remove(id);
            if (person != null) {
                nameMap.getOrDefault(person.name(), Collections.emptySet()).remove(id);
                addressMap.getOrDefault(person.address(), Collections.emptySet()).remove(id);
                phoneMap.getOrDefault(person.phoneNumber(), Collections.emptySet()).remove(id);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @SneakyThrows
    @Override
    public List<Person> findByName(String name) {
        boolean wasLocked = lock.readLock().tryLock(TIMEOUT, TimeUnit.SECONDS);
        if (!wasLocked) {
            throw new TimeoutException("Timeout while searching by name.");
        }
        return findByAttribute(nameMap, name);
    }

    @SneakyThrows
    @Override
    public List<Person> findByAddress(String address) {
        boolean wasLocked = lock.readLock().tryLock(TIMEOUT, TimeUnit.SECONDS);
        if (!wasLocked) {
            throw new TimeoutException("Timeout while searching by address.");
        }
        return findByAttribute(addressMap, address);
    }

    @SneakyThrows
    @Override
    public List<Person> findByPhone(String phone) {
        boolean wasLocked = lock.readLock().tryLock(TIMEOUT, TimeUnit.SECONDS);
        if (!wasLocked) {
            throw new TimeoutException("Timeout while searching by phone.");
        }
        return findByAttribute(phoneMap, phone);
    }

    private List<Person> findByAttribute(Map<String, Set<Integer>> map, String attribute) {
        lock.readLock().lock();
        try {
            return map
                .getOrDefault(attribute, Collections.emptySet())
                .stream()
                .map(idMap::get)
                .toList();
        } finally {
            lock.readLock().unlock();
        }
    }
}
