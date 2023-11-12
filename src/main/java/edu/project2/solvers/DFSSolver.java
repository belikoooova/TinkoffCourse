package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.RoutNotFoundException;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.MazeUtils.getNeighboursCoords;
import static edu.project2.MazeUtils.isWrongCoords;

public class DFSSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Coordinate newStart = new Coordinate(start.row() * 2 + 1, start.col() * 2 + 1);
        Coordinate newEnd = new Coordinate(end.row() * 2 + 1, end.col() * 2 + 1);
        if (isWrongCoords(newStart, maze.getHeight(), maze.getWidth())
            || isWrongCoords(newEnd, maze.getHeight(), maze.getWidth())) {
            throw new RoutNotFoundException(String.format(
                "Coordinates must be between 0 and %d for X and 0 and %d for Y",
                (maze.getHeight() - 1) / 2 - 1,
                (maze.getWidth() - 1) / 2 - 1
            ));
        }
        boolean[][] isVisited = new boolean[maze.getHeight()][maze.getWidth()];
        List<Coordinate> path = new ArrayList<>();
        if (!dfs(newStart, newEnd, maze, isVisited, path)) {
            throw new FindException();
        }
        path.add(newStart);
        return path;
    }

    private boolean dfs(Coordinate start, Coordinate end, Maze maze, boolean[][] isVisited, List<Coordinate> path) {
        isVisited[start.row()][start.col()] = true;
        if (start.col() == end.col() && start.row() == end.row()) {
            return true;
        }
        var neighboursCoords =
            getNeighboursCoords(start.row(), start.col(), maze.getHeight(), maze.getWidth(), isVisited);
        for (var neighbour : neighboursCoords) {
            if (isVisited[neighbour.row()][neighbour.col()]) {
                continue;
            }
            Coordinate betweenCoordinate =
                new Coordinate((start.row() + neighbour.row()) / 2, (start.col() + neighbour.col()) / 2);
            if (maze.getGrid()[betweenCoordinate.row()][betweenCoordinate.col()].type() == Cell.Type.WALL) {
                continue;
            }
            if (dfs(neighbour, end, maze, isVisited, path)) {
                path.add(betweenCoordinate);
                path.add(neighbour);
                return true;
            }
        }
        return false;
    }
}
