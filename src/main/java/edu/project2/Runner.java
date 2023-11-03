package edu.project2;

import java.util.List;
import java.util.Scanner;

public class Runner {
    Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println(HumanReadableMessage.HELLO);
        do {
            Generator generator = getGenerator();
            Maze maze = null;
            do {
                try {
                    System.out.println(HumanReadableMessage.INPUT_HEIGHT);
                    int height = scanner.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_WIDTH);
                    int width = scanner.nextInt();
                    maze = generator.generate(height, width);
                } catch (Exception ignored) {
                }
            } while (maze == null);
            Renderer renderer = new Renderer();
            System.out.print(renderer.render(maze));
            scanner.nextLine();
            Solver solver = getSolver();
            List<Coordinate> path = null;
            do {
                try {
                    System.out.println(HumanReadableMessage.INPUT_START_X);
                    int start_x = scanner.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_START_Y);
                    int start_y = scanner.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_END_X);
                    int end_x = scanner.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_END_Y);
                    int end_y = scanner.nextInt();
                    path = solver.solve(maze, new Coordinate(start_x, start_y), new Coordinate(end_x, end_y));
                } catch (IllegalArgumentException ex) {
                    System.out.printf(
                        (HumanReadableMessage.ERROR_COORDS.toString()) + "%n",
                        (maze.getHeight() - 1) / 2,
                        (maze.getWidth() - 1) / 2
                    );
                }
            } while (path == null);
            System.out.print(renderer.render(maze, path));
            scanner.nextLine();
        } while (shouldContinueGaming());
    }

    private boolean shouldContinueGaming() {
        System.out.println(HumanReadableMessage.EXIT);
        String input = scanner.nextLine();
        return input != null && !input.equalsIgnoreCase("yes");
    }

    private Generator getGenerator() {
        String userInput;
        do {
            System.out.print(HumanReadableMessage.CHOOSE_GENERATOR_TYPE);
            userInput = scanner.nextLine();
        } while (!"dfs".equalsIgnoreCase(userInput) && !"bfs".equalsIgnoreCase(userInput));
        if ("dfs".equalsIgnoreCase(userInput)) {
            return new DFSGenerator();
        }
        return new BFSGenerator();
    }

    private Solver getSolver() {
        String userInput;
        do {
            System.out.print(HumanReadableMessage.CHOOSE_SOLVER_TYPE);
            userInput = scanner.nextLine();
        } while (!"dfs".equalsIgnoreCase(userInput) && !"bfs".equalsIgnoreCase(userInput));
        if ("dfs".equalsIgnoreCase(userInput)) {
            return new DFSSolver();
        }
        return new BFSSolver();
    }
}
