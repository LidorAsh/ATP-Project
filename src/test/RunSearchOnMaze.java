package test;
        import algorithms.mazeGenerators.IMazeGenerator;
        import algorithms.mazeGenerators.Maze;
        import algorithms.mazeGenerators.MyMazeGenerator;
        import algorithms.mazeGenerators.Position;
        import algorithms.search.*;
        import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
//        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
//        solveProblem(searchableMaze, new BestFirstSearch());

//        AState s = new MazeState(new Position(1,1));
//        AState s1 = new MazeState(new Position(2,1));
//        if(s.equals(s1))
//            System.out.println("same");

//        Position p1 = new Position(1,1);
//        Position p2 = new Position(1,1);
//        if(p1 == p2)
//            System.out.println("same");

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}