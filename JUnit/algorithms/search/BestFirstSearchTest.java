package algorithms.search;
//package test;
import algorithms.maze3D.*;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;

class BestFirstSearchTest {
    @Test
    void solve() {
        IMazeGenerator mg = new MyMazeGenerator();
        try {
            mg.generate(0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            new SearchableMaze(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            IMaze3DGenerator mg3 = new MyMaze3DGenerator();
            Maze3D maze3 = mg3.generate(3, 10, 10);
           new SearchableMaze3D(maze3);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            new SearchableMaze3D(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        Maze maze = mg.generate(10, 10);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch best = new BestFirstSearch();
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        if (solutionPath.isEmpty())
            fail();
    }
}