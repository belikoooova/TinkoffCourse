package edu.project2;

import edu.project2.generators.BFSGenerator;
import edu.project2.generators.DFSGenerator;
import edu.project2.generators.Generator;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.DFSSolver;
import edu.project2.solvers.ParallelBFSSolver;
import edu.project2.solvers.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.module.FindException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectTest {
    private static final int FIVE = 5;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int THREE = 3;
    private static final int NEGATIVE = -1;
    public static final String BACKGROUND_BLACK = "\033[40m";
    public static final String BACKGROUND_RED = "\033[41m";
    public static final String RESET = "\033[0m";

    @Test
    @DisplayName("Test that finds way")
    void findsWay() {
        // given
        Cell[][] grid = new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.WALL), new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE),
                new Cell(2, 2, Cell.Type.WALL), new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.WALL),
                new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL)}
        };
        Maze maze = new Maze(FIVE, FIVE, grid);
        boolean[][] isVisited = new boolean[FIVE][FIVE];
        Solver dfsSolver = new DFSSolver();
        Solver bfsSolver = new BFSSolver();

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ParallelBFSSolver parallelBfsSolver =
            new ParallelBFSSolver(maze, new Coordinate(ONE, ONE), new Coordinate(THREE, THREE), isVisited);

        // when ang then
        assertDoesNotThrow(() -> {
            dfsSolver.solve(maze, new Coordinate(ZERO, ZERO), new Coordinate(ONE, ONE));
        });
        assertDoesNotThrow(() -> {
            bfsSolver.solve(maze, new Coordinate(ZERO, ZERO), new Coordinate(ONE, ONE));
        });
        assertDoesNotThrow(() -> {
            List<Coordinate> path = forkJoinPool.invoke(parallelBfsSolver);
            assertNotNull(path);
            assertFalse(path.isEmpty());
        });
    }

    @Test
    @DisplayName("Test that does not find way")
    void doesNotFindWay() {
        // given
        Cell[][] grid = new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.WALL), new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE),
                new Cell(2, 2, Cell.Type.WALL), new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.WALL), new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.WALL),
                new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL)}
        };
        Maze maze = new Maze(FIVE, FIVE, grid);
        Solver dfsSolver = new DFSSolver();
        Solver bfsSolver = new BFSSolver();

        // when ang then
        assertThrows(FindException.class, () -> {
            dfsSolver.solve(maze, new Coordinate(ZERO, ZERO), new Coordinate(ONE, ONE));
        });
        assertThrows(FindException.class, () -> {
            bfsSolver.solve(maze, new Coordinate(ZERO, ZERO), new Coordinate(ONE, ONE));
        });
    }

    @Test
    @DisplayName("Test draw")
    void draw() {
        // given
        Cell[][] grid = new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.WALL), new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE),
                new Cell(2, 2, Cell.Type.WALL), new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.WALL)},
            new Cell[] {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.WALL),
                new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL)}
        };
        Maze maze = new Maze(FIVE, FIVE, grid);
        StringBuilder expexted = new StringBuilder();
        expexted.append(
            BACKGROUND_BLACK + "   " + RESET + BACKGROUND_BLACK + "   " + RESET + BACKGROUND_BLACK + "   " + RESET +
                BACKGROUND_BLACK + "   " + RESET + BACKGROUND_BLACK + "   " + RESET + '\n');
        expexted.append(
            BACKGROUND_BLACK + "   " + RESET + BACKGROUND_RED + "   " + RESET + BACKGROUND_BLACK + "   " + RESET +
                BACKGROUND_RED + "   " + RESET + BACKGROUND_BLACK + "   " + RESET + '\n');
        expexted.append(
            BACKGROUND_BLACK + "   " + RESET + BACKGROUND_RED + "   " + RESET + BACKGROUND_BLACK + "   " + RESET +
                BACKGROUND_RED + "   " + RESET + BACKGROUND_BLACK + "   " + RESET + '\n');
        expexted.append(
            BACKGROUND_BLACK + "   " + RESET + BACKGROUND_RED + "   " + RESET + BACKGROUND_RED + "   " + RESET +
                BACKGROUND_RED + "   " + RESET + BACKGROUND_BLACK + "   " + RESET + '\n');
        expexted.append(
            BACKGROUND_BLACK + "   " + RESET + BACKGROUND_BLACK + "   " + RESET + BACKGROUND_BLACK + "   " + RESET +
                BACKGROUND_BLACK + "   " + RESET + BACKGROUND_BLACK + "   " + RESET + '\n');
        Renderer renderer = new Renderer();

        // when
        String result = renderer.render(maze);

        // then
        assertThat(result).isEqualTo(expexted.toString());
    }

    @Test
    @DisplayName("Test incorrect size")
    void incorrectSize() {
        // given
        Generator dfsGenerator = new DFSGenerator();
        Generator bfsGenerator = new BFSGenerator();

        // when ang then
        assertThrows(IllegalArgumentException.class, () -> dfsGenerator.generate(NEGATIVE, ONE));
        assertThrows(IllegalArgumentException.class, () -> bfsGenerator.generate(NEGATIVE, ONE));
    }
}
