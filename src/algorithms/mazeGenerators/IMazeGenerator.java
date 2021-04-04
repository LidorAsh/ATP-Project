package algorithms.mazeGenerators;

/**
 * This is the interface for the maze generators
 */
public interface IMazeGenerator {
    Maze generate(int rows, int columns);
    long measureAlgorithmTimeMillis(int rows, int columns);
}
