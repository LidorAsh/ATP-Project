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

            SearchableMaze searchableMaze = new SearchableMaze(maze);
            ISearchingAlgorithm searcher = new BreadthFirstSearch(); // configurations
            Solution solution = searcher.solve(searchableMaze);

//            Thread.sleep(10000);

            toClient.writeObject(solution);
            toClient.flush();

//            toClient.close();
//            fromClient.close();

        } catch (EOFException e) {
            // ... this is fine

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }



}
