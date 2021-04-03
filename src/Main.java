import algorithms.mazeGenerators.*;

public class Main {

    public static void main(String[] args) {

        IMazeGenerator maze = new SimpleMazeGenerator();
        Maze m = maze.generate(20,20);
        m.print();

        //System.out.println(maze.measureAlgorithmTimeMillis(1000,1000));


    }
}
