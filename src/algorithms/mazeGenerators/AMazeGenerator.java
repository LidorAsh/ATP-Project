package algorithms.mazeGenerators;

/**
 * This class is an abstract class which implements the maze generator interface
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /*
     * Method that measure the time taken to generate a maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start = System.currentTimeMillis();
        generate(rows, columns);
        long end = System.currentTimeMillis();
        return (end - start);
    }
}
