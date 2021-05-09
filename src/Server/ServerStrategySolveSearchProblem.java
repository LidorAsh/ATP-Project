package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private final Object lock = new Object();

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            Maze maze = (Maze) fromClient.readObject();

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String filepath = tempDirectoryPath + "\\" + maze.hashCode();

            File solFile = new File(filepath);

            Solution solution;

            if (solFile.exists()) { // whether there is solution for the current maze
                FileInputStream fileIn = new FileInputStream(filepath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                Object obj = objectIn.readObject();
                solution = (Solution) obj;
                objectIn.close();
            }

            else { // whether there is no solution for the current maze
                SearchableMaze searchableMaze = new SearchableMaze(maze);

                Configurations conf = Configurations.getInstance();
                String generator = conf.getMazeSearchingAlgorithm();

                ISearchingAlgorithm searcher = null;

                if (generator.equalsIgnoreCase("DFS"))
                    searcher = new DepthFirstSearch();
                else if (generator.equalsIgnoreCase("BFS"))
                    searcher = new BreadthFirstSearch();
                else if (generator.equalsIgnoreCase("BEST"))
                    searcher = new BestFirstSearch();

                solution = searcher.solve(searchableMaze);

                synchronized (lock) {
                    FileOutputStream fileOut = new FileOutputStream(filepath);
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(solution);
                    objectOut.close();
                }
            }

            toClient.writeObject(solution);
            toClient.flush();

            toClient.close();
            fromClient.close();

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
