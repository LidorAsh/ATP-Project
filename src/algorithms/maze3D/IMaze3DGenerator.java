package algorithms.maze3D;

public interface IMaze3DGenerator {
    //create a 3D maze using given specs
    Maze3D generate(int depth, int row, int column);
    //count how long does it take to generate the maze
    long measureAlgorithmTimeMillis(int depth, int row, int column);
}
