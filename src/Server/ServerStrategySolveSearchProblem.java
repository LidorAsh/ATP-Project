package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            //InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            //Object o = fromClient.readObject();
            Maze maze = (Maze) fromClient.readObject();

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String filepath = tempDirectoryPath + "\\" + maze.hashCode();

            File solFile = new File(filepath);

            Solution solution;

            if (solFile.exists()) {
                FileInputStream fileIn = new FileInputStream(filepath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                Object obj = objectIn.readObject();
                solution = (Solution) obj;
                objectIn.close();
            }

            else {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                ISearchingAlgorithm searcher = new BreadthFirstSearch(); // configurations
                solution = searcher.solve(searchableMaze);

                FileOutputStream fileOut = new FileOutputStream(filepath);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(solution);
                objectOut.close();
            }

            toClient.writeObject(solution);
            toClient.flush();

//            toClient.close();
//            fromClient.close();

        } //catch (EOFException e) {
            // ... this is fine

        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }



}
