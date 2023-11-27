package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task1Test {
    private static final int OLD_SIZE = 2;
    private static final int NEW_SIZE = 1;

    @Test
    @DisplayName("Put and Contains")
    void putAndContains() throws IOException {
        // given
        DiskMap diskMap = new DiskMap();

        // when
        diskMap.put("Hello", "It's me");
        diskMap.put("Nikola", "Tesla");

        // then
        assertTrue(diskMap.containsKey("Hello"));
        assertTrue(diskMap.containsKey("Nikola"));
        assertFalse(diskMap.containsKey("Steve"));
        assertTrue(diskMap.containsValue("It's me"));
        assertTrue(diskMap.containsValue("Tesla"));
        assertFalse(diskMap.containsValue("Jobs"));
    }

    @Test
    @DisplayName("Remove (and inner get) and size")
    void removeAndSize() throws IOException {
        // given
        DiskMap diskMap = new DiskMap();

        // when
        diskMap.put("Hello", "It's me");
        diskMap.put("Nikola", "Tesla");

        // then
        assertThat(diskMap.size()).isEqualTo(OLD_SIZE);
        assertThat(diskMap.remove("Hello")).isEqualTo("It's me");
        assertThat(diskMap.size()).isEqualTo(NEW_SIZE);
        assertFalse(diskMap.containsKey("Hello"));
    }

    @Test
    @DisplayName("Put with key that already exists")
    void putAlreadyExists() throws IOException {
        // given
        DiskMap diskMap = new DiskMap();

        // when
        diskMap.put("Hello", "It's me");

        // then
        assertThat(diskMap.put("Hello", "Tesla")).isEqualTo("It's me");
        assertThat(diskMap.get("Hello")).isEqualTo("Tesla");
    }

    @Test
    @DisplayName("Put all and clear")
    void putAllAndClear() throws IOException {
        // given
        DiskMap diskMap = new DiskMap();

        // when
        diskMap.putAll(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Hello", "It's me"),
            new AbstractMap.SimpleEntry<>("Nikola", "Tesla"),
            new AbstractMap.SimpleEntry<>("Steve", "Jobs")
        ));

        // then
        assertTrue(diskMap.containsKey("Hello"));
        assertTrue(diskMap.containsKey("Nikola"));
        assertTrue(diskMap.containsKey("Steve"));
        assertTrue(diskMap.containsValue("It's me"));
        assertTrue(diskMap.containsValue("Tesla"));
        assertTrue(diskMap.containsValue("Jobs"));

        // when
        diskMap.clear();
        assertTrue(diskMap.isEmpty());
    }
}
