package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

@SuppressWarnings("MultipleStringLiterals")
public class StatsCollector {
    private static final int OPERATIONS_AMOUNT = 4;
    private static final int TIMEOUT = 60;
    private final Map<Command, List<Double>> dataByCommand = new ConcurrentHashMap<>();
    private final ExecutorService service = Executors.newFixedThreadPool(OPERATIONS_AMOUNT);

    public void push(String commandString, double[] data) {
        Command command = Command.getByString(commandString);
        addToMap(command, data);
    }

    @SneakyThrows
    public Map<String, Double> getStats() {
        Map<String, Double> stats = new ConcurrentHashMap<>();
        try {
            Future<?> sumFuture = service.submit(() -> putSum(stats));
            Future<?> meanFuture = service.submit(() -> putMean(stats));
            Future<?> maxFuture = service.submit(() -> putMax(stats));
            Future<?> minFuture = service.submit(() -> putMin(stats));

            sumFuture.get();
            meanFuture.get();
            maxFuture.get();
            minFuture.get();
        } finally {
            service.shutdown();
            try {
                if (!service.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    service.shutdownNow();
                }
            } catch (InterruptedException e) {
                service.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        return stats;
    }

    private void putSum(Map<String, Double> stats) {
        double sum = dataByCommand
            .getOrDefault(Command.SUM, Collections.emptyList())
            .stream()
            .reduce(0.0, Double::sum);
        stats.put(Command.SUM.commandString, sum);
    }

    private void putMean(Map<String, Double> stats) {
        double mean = dataByCommand
            .getOrDefault(Command.MEAN, Collections.emptyList())
            .stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
        stats.put(Command.MEAN.commandString, mean);
    }

    private void putMin(Map<String, Double> stats) {
        double min = dataByCommand
            .getOrDefault(Command.MIN, Collections.emptyList())
            .stream()
            .min(Double::compareTo)
            .orElse(0.0);
        stats.put(Command.MIN.commandString, min);
    }

    private void putMax(Map<String, Double> stats) {
        double max = dataByCommand
            .getOrDefault(Command.MAX, Collections.emptyList())
            .stream()
            .max(Double::compareTo)
            .orElse(0.0);
        stats.put(Command.MAX.commandString, max);
    }

    private void addToMap(Command command, double[] data) {
        dataByCommand.putIfAbsent(command, new ArrayList<>());
        dataByCommand.get(command).addAll(Arrays.stream(data).boxed().toList());
    }

    private enum Command {
        SUM("sum"),
        MEAN("mean"),
        MIN("min"),
        MAX("max");

        private final String commandString;

        Command(String commandString) {
            this.commandString = commandString;
        }

        @Override
        public String toString() {
            return this.commandString;
        }

        public static Command getByString(String stringCommand) {
            return switch (stringCommand) {
                case "sum" -> Command.SUM;
                case "mean" -> Command.MEAN;
                case "min" -> Command.MIN;
                case "max" -> Command.MAX;
                default -> throw new UnsupportedOperationException("This operation hasn't been developed yet.");
            };
        }
    }
}
