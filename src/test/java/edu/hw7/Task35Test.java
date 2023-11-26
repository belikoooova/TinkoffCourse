package edu.hw7;

import edu.hw7.task3.DuplicatedPersonIdException;
import edu.hw7.task3.InMemoryPersonDatabase;
import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task35Test {
    private static final int NOT_EXISTING_INDEX = 100;
    private static final int SAME_SIZE_AMOUNT = 2;
    private static final int NUMBER_OF_THREADS = 10;
    private static final Person HELENA = new Person(1, "Helena", "123 Main St", "+79064670329");
    private static final Person ANN = new Person(2, "Ann", "123 Main St", "+79064670329");
    private static final Person SAM = new Person(3, "Sam", "224 Main St", "+79060170329");

    @Test
    void testAddPersonSuccess() {
        // given
        PersonDatabase database = new InMemoryPersonDatabase();
        Person person = new Person(1, "Sam", "123 Main St", "+79064670329");

        // when
        database.add(person);

        // then
        assertEquals(1, database.findByName("Sam").size());
        assertEquals(1, database.findByAddress("123 Main St").size());
        assertEquals(1, database.findByPhone("+79064670329").size());
    }

    @Test
    void testAddPersonDuplicatedId() {
        // given
        PersonDatabase database = new InMemoryPersonDatabase();
        Person person1 = new Person(1, "Sam", "123 Main St", "+79064670329");
        Person person2 = new Person(1, "Ann", "124 Main St", "+79099990329");
        database.add(person1);

        // when and then
        assertThrows(DuplicatedPersonIdException.class, () -> database.add(person2));
    }

    @Test
    void testDeletePerson() {
        // given
        var database = getDefaultDatabase();

        // when
        database.delete(1);

        // then
        assertTrue(database.findByName("Helena").isEmpty());
    }

    @Test
    void testDeleteNonExistingPerson() {
        // given
        var database = getDefaultDatabase();

        // when and then
        assertDoesNotThrow(() -> database.delete(NOT_EXISTING_INDEX));
    }

    @Test
    void testFindByName() {
        // given
        var database = getDefaultDatabase();

        // when
        List<Person> results = database.findByName("Helena");

        // then
        assertEquals(1, results.size());
        assertEquals(HELENA, results.get(0));
    }

    @Test
    void testFindByAddress() {
        // given
        var database = getDefaultDatabase();

        // when
        List<Person> results = database.findByAddress("123 Main St");

        // then
        assertEquals(SAME_SIZE_AMOUNT, results.size());
        assertTrue(results.contains(HELENA));
        assertTrue(results.contains(ANN));
    }

    @SneakyThrows
    @Test
    void testConcurrentRead() {
        // given
        PersonDatabase database = new InMemoryPersonDatabase();
        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);
        for (int id = 0; id < NUMBER_OF_THREADS; ++id) {
            String name = "NAME" + id;
            String address = "ADDRESS" + id;
            String phone = "PHONE" + id;
            Person person = new Person(id, name, address, phone);
            database.add(person);
        }

        // when
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            int id = i;
            service.submit(() -> {
                try {
                    String name = "NAME" + id;
                    String address = "ADDRESS" + id;
                    String phone = "PHONE" + id;
                    Person person = new Person(id, name, address, phone);
                    database.add(person);
                    List<Person> foundedByName = database.findByName(name);
                    List<Person> foundedByAddress = database.findByName(phone);
                    List<Person> foundedByPhone = database.findByName(phone);

                    // then
                    assertTrue(foundedByName.contains(person));
                    assertTrue(foundedByAddress.contains(person));
                    assertTrue(foundedByPhone.contains(person));
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        service.shutdown();
    }

    @SneakyThrows
    @Test
    void testConcurrentReadAndWrite() {
        // given
        PersonDatabase database = new InMemoryPersonDatabase();
        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        // when
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            int id = i;
            String name = "NAME" + id;
            String address = "ADDRESS" + id;
            String phone = "PHONE" + id;
            service.submit(() -> {
                try {
                    Person person = new Person(id, name, address, phone);
                    database.add(person);
                } finally {
                    latch.countDown();
                }
            });

            service.submit(() -> {
                try {
                    List<Person> foundedByName = database.findByName(name);
                    List<Person> foundedByAddress = database.findByName(phone);
                    List<Person> foundedByPhone = database.findByName(phone);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        service.shutdown();
    }

    private PersonDatabase getDefaultDatabase() {
        PersonDatabase database = new InMemoryPersonDatabase();
        database.add(HELENA);
        database.add(ANN);
        database.add(SAM);
        return database;
    }
}
