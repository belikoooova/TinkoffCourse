package edu.project2;

import java.util.ArrayList;
import java.util.List;
import static edu.project2.MazeUtilities.getNeighboursCoords;
import static edu.project2.MazeUtilities.isWrongCoords;

public class DFSSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Coordinate newStart = new Coordinate(start.row() * 2 + 1, start.col() * 2 + 1);
        Coordinate newEnd = new Coordinate(end.row() * 2 + 1, end.col() * 2 + 1);
        if (isWrongCoords(newStart, maze.getHeight(), maze.getWidth()) || isWrongCoords(newEnd, maze.getHeight(), maze.getWidth())) {
            throw new IllegalArgumentException();
        }
        boolean[][] isVisited = new boolean[maze.getHeight()][maze.getWidth()];
        List<Coordinate> path = new ArrayList<>();
        dfs(newStart, newEnd, maze, isVisited, path);
        path.add(newStart);
        return path;
    }

    private boolean dfs(Coordinate start, Coordinate end, Maze maze, boolean[][] isVisited, List<Coordinate> path) {
        isVisited[start.row()][start.col()] = true;
        if (start.col() == end.col() && start.row() == end.row()) {
            return true;
        }
        var neighboursCoords = getNeighboursCoords(start.row(), start.col(), maze.getHeight(), maze.getWidth(), isVisited);
        for (var neighbour : neighboursCoords) {
            if (!isVisited[neighbour.row()][neighbour.col()]) {
                Coordinate betweenCoordinate = new Coordinate((start.row() + neighbour.row()) / 2, (start.col() + neighbour.col()) / 2);
                if (maze.getGrid()[betweenCoordinate.row()][betweenCoordinate.col()].getType() == Cell.Type.WALL) {
                    continue;
                }
                if (dfs(neighbour, end, maze, isVisited, path)) {
                    path.add(betweenCoordinate);
                    path.add(neighbour);
                    return true;
                }
            }
        }
        return false;
    }
}
