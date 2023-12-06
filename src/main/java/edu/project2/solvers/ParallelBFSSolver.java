package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import static edu.project2.MazeUtils.getNeighboursCoords;

public class ParallelBFSSolver extends RecursiveTask<List<Coordinate>> {
    private final Maze maze;
    private final Coordinate start;
    private final Coordinate end;
    private final boolean[][] isVisited;

    public ParallelBFSSolver(Maze maze, Coordinate start, Coordinate end, boolean[][] isVisited) {
        this.maze = maze;
        this.start = start;
        this.end = end;
        this.isVisited = isVisited;
    }

    @Override
    public List<Coordinate> compute() {
        if (start.equals(end)) {
            List<Coordinate> path = new ArrayList<>();
            path.add(end);
            return path;
        }

        List<ParallelBFSSolver> subtasks = new ArrayList<>();

        var current = start;
        var probableNeighbours =
            getNeighboursCoords(current.row(), current.col(), maze.getHeight(), maze.getWidth(), isVisited);
        List<Coordinate> neighbours = new ArrayList<>();
        for (var neighbour : probableNeighbours) {
            Coordinate betweenCoordinate =
                new Coordinate((current.row() + neighbour.row()) / 2, (current.col() + neighbour.col()) / 2);
            if (maze.getGrid()[betweenCoordinate.row()][betweenCoordinate.col()].type() != Cell.Type.WALL) {
                neighbours.add(neighbour);
            }
        }

        for (Coordinate neighbor : neighbours) {
            if (!isVisited[neighbor.row()][neighbor.col()]) {
                isVisited[neighbor.row()][neighbor.col()] = true;
                ParallelBFSSolver task = new ParallelBFSSolver(maze, neighbor, end, isVisited);
                task.fork();
                subtasks.add(task);
            }
        }

        List<Coordinate> shortestPath = null;
        for (ParallelBFSSolver task : subtasks) {
            List<Coordinate> path = task.join();
            if (shortestPath == null || (path != null && path.size() < shortestPath.size())) {
                shortestPath = path;
            }
        }

        if (shortestPath != null) {
            shortestPath.add(0, start);
        }
        return shortestPath;
    }
}
