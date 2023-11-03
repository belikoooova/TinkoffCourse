package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class MazeUtilities {
    private static final int[] X_SHIFTS = new int[] {2, 0, -2, 0};
    private static final int[] Y_SHIFTS = new int[] {0, 2, 0, -2};

    private MazeUtilities() {}

    public static Maze initialFill(int height, int width) {
        Maze maze = new Maze(height * 2 + 1, width * 2 + 1);
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (i % 2 != 0 && j % 2 != 0) {
                    maze.getGrid()[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    maze.getGrid()[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }
        return maze;
    }

    public static List<Coordinate> getNeighboursCoords(
        int cellHeight,
        int cellWidth,
        int mazeHeight,
        int mazeWidth,
        boolean[][] isVisited
    ) {
        List<Coordinate> newCellsCoords = new ArrayList<>();
        for (int i = 0; i < X_SHIFTS.length; ++i) {
            int newCellHeight = cellHeight + X_SHIFTS[i];
            int newCellWidth = cellWidth + Y_SHIFTS[i];
            if (newCellHeight >= 0 && newCellHeight < mazeHeight && newCellWidth >= 0 && newCellWidth < mazeWidth &&
                !isVisited[newCellHeight][newCellWidth]) {
                newCellsCoords.add(new Coordinate(newCellHeight, newCellWidth));
            }
        }
        return newCellsCoords;
    }

    public static boolean isWrongCoords(Coordinate x, int limitX, int limitY) {
        if (x.col() < 0 || x.row() < 0) {
            return true;
        }
        if (x.row() >= limitX || x.col() >= limitY) {
            return true;
        }
        return false;
    }
}
