package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        Random rand = new Random();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                map[i][j] = rand.nextInt(2);

        int randRow = rand.nextInt(rows);
        int end = randRow;

        for (int i = 0; i < columns; i++)
            map[randRow][i] = 0;

        Maze maze = new Maze(map, new Position(randRow,0), new Position(randRow, columns - 1));
        return maze;
    }

}
