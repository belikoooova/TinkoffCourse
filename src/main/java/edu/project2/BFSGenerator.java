package edu.project2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import static edu.project2.MazeUtilities.initialFill;
import static edu.project2.MazeUtilities.getNeighboursCoords;

public class BFSGenerator implements Generator {
    private static final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        Maze maze = initialFill(height, width);
        boolean[][] isVisited = new boolean[height * 2 + 1][width * 2 + 1];
        bfs(maze, isVisited);
        return maze;
    }

    private void bfs(Maze maze, boolean[][] isVisited) {
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate start = new Coordinate(1, 1);
        isVisited[start.row()][start.col()] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            var neighbours =
                getNeighboursCoords(current.row(), current.col(), maze.getHeight(), maze.getWidth(), isVisited);
            if (!neighbours.isEmpty()) {
                queue.add(current);
                var neighbour = neighbours.get(random.nextInt(neighbours.size()));
                maze.getGrid()[(current.row() + neighbour.row()) / 2][(current.col() + neighbour.col()) / 2].setType(
                    Cell.Type.PASSAGE);
                isVisited[neighbour.row()][neighbour.col()] = true;
                queue.add(neighbour);
            }
        }
    }
}
