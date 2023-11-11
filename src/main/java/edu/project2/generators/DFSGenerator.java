package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.Collections;
import static edu.project2.MazeUtils.fillInitially;
import static edu.project2.MazeUtils.getNeighboursCoords;

public class DFSGenerator implements Generator {
    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width must be positive");
        }
        Maze maze = fillInitially(height, width);
        boolean[][] isVisited = new boolean[height * 2 + 1][width * 2 + 1];
        dfs(1, 1, maze, isVisited);
        return maze;
    }

    private void dfs(int cellHeight, int cellWidth, Maze maze, boolean[][] isVisited) {
        isVisited[cellHeight][cellWidth] = true;
        var neighboursCoords = getNeighboursCoords(cellHeight, cellWidth, maze.getHeight(), maze.getWidth(), isVisited);
        Collections.shuffle(neighboursCoords);
        for (var neighbour : neighboursCoords) {
            if (!isVisited[neighbour.row()][neighbour.col()]) {
                maze.getGrid()[(cellHeight + neighbour.row()) / 2][(cellWidth + neighbour.col()) / 2] =
                    new Cell((cellHeight + neighbour.row()) / 2, (cellWidth + neighbour.col()) / 2, Cell.Type.PASSAGE);
                dfs(neighbour.row(), neighbour.col(), maze, isVisited);
            }
        }
    }
}
