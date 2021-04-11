package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /*
     * Method that measure the time taken to generate a 3D maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start = System.currentTimeMillis();
        generate(depth, row, column);
        long end = System.currentTimeMillis();
        return (end - start);
    }
}
