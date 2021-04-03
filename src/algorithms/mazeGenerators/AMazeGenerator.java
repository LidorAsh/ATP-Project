package algorithms.mazeGenerators;

abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start = System.currentTimeMillis();
        generate(rows, columns);
        long end = System.currentTimeMillis();
        return (end - start);
    }
}
