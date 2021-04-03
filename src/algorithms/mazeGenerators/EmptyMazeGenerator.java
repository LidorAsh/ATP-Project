package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                map[i][j] = 0;
            }
        }
        Maze maze = new Maze(map, new Position(0,0), new Position(rows - 1, columns - 1));
        return maze;
    }
}
