package algorithms.mazeGenerators;

import java.util.Random;

/**
 * This class represent a simple maze (maze with many solutions)
 */
public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        Random rand = new Random();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                map[i][j] = rand.nextInt(2);

        int randRow = rand.nextInt(rows); // the row of the start and the end is random

        for (int i = 0; i < columns; i++)
            map[randRow][i] = 0;

        return new Maze(map, new Position(randRow,0), new Position(randRow, columns - 1));
    }

}
