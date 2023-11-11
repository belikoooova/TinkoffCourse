package edu.project2;

import java.util.List;

public class Renderer {
    public static final String BACKGROUND_BLACK = "\033[40m";
    public static final String FOREGROUND_BLACK = "\033[0;30m";
    public static final String BACKGROUND_RED = "\033[41m";
    public static final String RESET = "\033[0m";
    private static final String SPACE = "   ";

    public String render(Maze maze) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.getGrid()[i][j].type() == Cell.Type.WALL) {
                    stringBuilder.append(BACKGROUND_BLACK + SPACE + RESET);
                } else {
                    stringBuilder.append(BACKGROUND_RED + SPACE + RESET);
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.getGrid()[i][j].type() == Cell.Type.WALL) {
                    stringBuilder.append(BACKGROUND_BLACK + SPACE + RESET);
                } else if (contains(i, j, path)) {
                    stringBuilder.append(FOREGROUND_BLACK + BACKGROUND_RED + " ❤ " + RESET);
                } else {
                    stringBuilder.append(BACKGROUND_RED + SPACE + RESET);
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    private static boolean contains(int x, int y, List<Coordinate> path) {
        for (var coord : path) {
            if (coord.row() == x && coord.col() == y) {
                return true;
            }
        }
        return false;
    }
}
