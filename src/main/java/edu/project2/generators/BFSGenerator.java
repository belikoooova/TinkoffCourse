package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import static edu.project2.MazeUtils.fillInitially;
import static edu.project2.MazeUtils.getNeighboursCoords;

public class BFSGenerator implements Generator {
    private static final Random RANDOM = new Random();

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width must be positive");
        }
        Maze maze = fillInitially(height, width);
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
                var neighbour = neighbours.get(RANDOM.nextInt(neighbours.size()));
                maze.getGrid()[(current.row() + neighbour.row()) / 2][(current.col() + neighbour.col()) / 2] =
                    new Cell(
                        (current.row() + neighbour.row()) / 2,
                        (current.col() + neighbour.col()) / 2,
                        Cell.Type.PASSAGE
                    );
                isVisited[neighbour.row()][neighbour.col()] = true;
                queue.add(neighbour);
            }
        }
    }
}
