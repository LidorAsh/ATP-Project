package algorithms.mazeGenerators;

/**
 * This class represent an empty maze (maze without walls)
 */
public class EmptyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) throws IllegalArgumentException{
        if (rows < 2 || columns < 2)
            throw new IllegalArgumentException("Maze size must be at least 2*2");

        int[][] map = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                map[i][j] = 0;
            }
        }
        return new Maze(map, new Position(0,0), new Position(rows - 1, columns - 1));
    }
}
