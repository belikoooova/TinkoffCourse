package edu.project2;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class Runner {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DFS = "dfs";
    private static final String BFS = "bfs";

    public void run() {
        System.out.println(HumanReadableMessage.HELLO);
        do {
            Generator generator = getGenerator();
            Maze maze = null;
            do {
                try {
                    System.out.println(HumanReadableMessage.INPUT_HEIGHT);
                    int height = SCANNER.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_WIDTH);
                    int width = SCANNER.nextInt();
                    maze = generator.generate(height, width);
                } catch (Exception ignored) {
                }
            } while (maze == null);
            Renderer renderer = new Renderer();
            System.out.print(renderer.render(maze));
            SCANNER.nextLine();
            Solver solver = getSolver();
            List<Coordinate> path = null;
            do {
                try {
                    System.out.println(HumanReadableMessage.INPUT_START_X);
                    int startX = SCANNER.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_START_Y);
                    int startY = SCANNER.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_END_X);
                    int endX = SCANNER.nextInt();
                    System.out.println(HumanReadableMessage.INPUT_END_Y);
                    int endY = SCANNER.nextInt();
                    path = solver.solve(maze, new Coordinate(startX, startY), new Coordinate(endX, endY));
                } catch (IllegalArgumentException ex) {
                    System.out.printf(
                        (HumanReadableMessage.ERROR_COORDS.toString()) + "%n",
                        (maze.getHeight() - 1) / 2,
                        (maze.getWidth() - 1) / 2
                    );
                }
            } while (path == null);
            System.out.print(renderer.render(maze, path));
            SCANNER.nextLine();
        } while (shouldContinueGaming());
    }

    private boolean shouldContinueGaming() {
        System.out.println(HumanReadableMessage.EXIT);
        String input = SCANNER.nextLine();
        return input != null && !input.equalsIgnoreCase("yes");
    }

    private Generator getGenerator() {
        String userInput;
        do {
            System.out.print(HumanReadableMessage.CHOOSE_GENERATOR_TYPE);
            userInput = SCANNER.nextLine();
        } while (!DFS.equalsIgnoreCase(userInput) && !BFS.equalsIgnoreCase(userInput));
        if (DFS.equalsIgnoreCase(userInput)) {
            return new DFSGenerator();
        }
        return new BFSGenerator();
    }

    private Solver getSolver() {
        String userInput;
        do {
            System.out.print(HumanReadableMessage.CHOOSE_SOLVER_TYPE);
            userInput = SCANNER.nextLine();
        } while (!DFS.equalsIgnoreCase(userInput) && !BFS.equalsIgnoreCase(userInput));
        if (DFS.equalsIgnoreCase(userInput)) {
            return new DFSSolver();
        }
        return new BFSSolver();
    }
}
