package edu.hw2;

import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.StableConnection;
import edu.hw2.task3.Connection;
import edu.hw2.task3.FaultyConnection;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {
    private static final int BIG_MAX_ATTEMPTS_VALUE = 5;
    private static final int SMALL_MAX_ATTEMPTS_VALUE = 1;

    @Test
    @DisplayName("Try get stable connection")
    void getStableConnection() {
        // given
        PopularCommandExecutor executor =
            new PopularCommandExecutor(new DefaultConnectionManager(), BIG_MAX_ATTEMPTS_VALUE);
        boolean wasExecuted;

        // when
        try {
            executor.updatePackages();
            wasExecuted = true;
        } catch (ConnectionException exception) {
            wasExecuted = false;
        }

        // then
        assertThat(wasExecuted).isTrue();
    }

    @Test
    @DisplayName("Try get not stable connection with DefaultConnectionManager")
    void getNotStableConnection() {
        // given
        PopularCommandExecutor executor =
            new PopularCommandExecutor(new DefaultConnectionManager(), SMALL_MAX_ATTEMPTS_VALUE);
        boolean wasExecuted;

        // when
        try {
            executor.updatePackages();
            wasExecuted = true;
        } catch (ConnectionException exception) {
            wasExecuted = false;
        }

        // then
        assertThat(wasExecuted).isFalse();
    }

    @Test
    @DisplayName("Get connection with FaultyConnectionManager")
    void GetConnection() {
        // given
        PopularCommandExecutor executor =
            new PopularCommandExecutor(new FaultyConnectionManager(), SMALL_MAX_ATTEMPTS_VALUE);
        boolean wasExecuted;

        // when
        try {
            executor.updatePackages();
            wasExecuted = true;
        } catch (ConnectionException exception) {
            wasExecuted = false;
        }

        // then
        assertThat(wasExecuted).isTrue();
    }
}
