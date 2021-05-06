package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.nio.channels.Channels;
import java.util.Arrays;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            //InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

            Object o = fromClient.readObject();
            int[] mazeSize = (int[]) o;

            IMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeSize[0], mazeSize[1]);
//            maze.print();
//            Thread.sleep(10000);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            SimpleCompressorOutputStream s = new SimpleCompressorOutputStream(bos);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);


            s.write(maze.toByteArray());
            s.flush();
            byte[] bytes = bos.toByteArray();
            toClient.writeObject(bytes);
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
