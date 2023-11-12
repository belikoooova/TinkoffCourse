package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.RoutNotFoundException;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import static edu.project2.MazeUtils.getNeighboursCoords;
import static edu.project2.MazeUtils.isWrongCoords;

public class BFSSolver implements Solver {
    private static final Random RANDOM = new Random();

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
        var previous = bfs(maze, isVisited, newStart);
        return restorePath(previous, newEnd);
    }

    private Coordinate[][] bfs(Maze maze, boolean[][] isVisited, Coordinate start) {
        Queue<Coordinate> queue = new LinkedList<>();
        isVisited[start.row()][start.col()] = true;
        Coordinate[][] prev = new Coordinate[maze.getHeight()][maze.getWidth()];
        prev[start.row()][start.col()] = new Coordinate(-1, -1);
        queue.add(start);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            var neighbours =
                getNeighboursCoords(current.row(), current.col(), maze.getHeight(), maze.getWidth(), isVisited);
            List<Coordinate> trueNeighbours = new ArrayList<>();

            for (var neighbour : neighbours) {
                Coordinate betweenCoordinate =
                    new Coordinate((current.row() + neighbour.row()) / 2, (current.col() + neighbour.col()) / 2);
                if (maze.getGrid()[betweenCoordinate.row()][betweenCoordinate.col()].type() != Cell.Type.WALL) {
                    trueNeighbours.add(neighbour);
                }
            }
            neighbours = trueNeighbours;

            if (!neighbours.isEmpty()) {
                queue.add(current);
                int index = RANDOM.nextInt(neighbours.size());
                var neighbour = neighbours.get(index);
                isVisited[neighbour.row()][neighbour.col()] = true;
                queue.add(neighbour);
                prev[neighbour.row()][neighbour.col()] = current;
            }
        }
        return prev;
    }

    private List<Coordinate> restorePath(Coordinate[][] previous, Coordinate end) {
        if (previous[end.row()][end.col()] == null) {
            throw new FindException("The way was not found");
        }
        List<Coordinate> path = new ArrayList<>();
        Coordinate cur = end;
        while (cur.row() != -1) {
            path.add(cur);
            var prev = previous[cur.row()][cur.col()];
            path.add(new Coordinate((cur.row() + prev.row()) / 2, (cur.col() + prev.col()) / 2));
            cur = prev;
        }
        return path;
    }
}
