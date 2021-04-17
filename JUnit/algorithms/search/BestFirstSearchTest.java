package algorithms.search;
//package test;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest
    {



        @Test
        void getNumberOfNodesEvaluated()
            {
                assertAll(this::getNumberOfNodesEvaluated);
            }
        private static ArrayList<AState> solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
            //Solve a searching problem with a searcher
            Solution solution = searcher.solve(domain);
            System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
            //Printing Solution Path
            System.out.println("Solution path:");
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            for (int i = 0; i < solutionPath.size(); i++) {
                System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
            }
            return solutionPath;
        }
        @Test
        void solve()
            {
                IMazeGenerator mg = new MyMazeGenerator();
                Maze maze = mg.generate(0, 0);
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                SearchableMaze searchableMaze1 = new SearchableMaze(null);
                IMaze3DGenerator mg3 = new MyMaze3DGenerator();
                Maze3D maze3 = mg3.generate(3, 10, 10);
                SearchableMaze3D searchableMaze3 = new SearchableMaze3D(maze3);
                SearchableMaze3D searchableMaze4 = new SearchableMaze3D(null);
                assertNotNull(solveProblem(searchableMaze3, new BestFirstSearch()));
                assertNotNull(solveProblem(searchableMaze4, new BestFirstSearch()));
            }
    }