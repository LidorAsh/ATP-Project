package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

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

            Configurations conf = Configurations.getInstance();
            String generator = conf.getMazeGeneratingAlgorithm();
            IMazeGenerator mazeGenerator = null;

            if (generator.equalsIgnoreCase("Empty"))
                mazeGenerator = new EmptyMazeGenerator();
            else if (generator.equalsIgnoreCase("Simple"))
                mazeGenerator = new SimpleMazeGenerator();
            else if (generator.equalsIgnoreCase("My"))
                mazeGenerator = new MyMazeGenerator();


            Maze maze = mazeGenerator.generate(mazeSize[0], mazeSize[1]);
//            maze.print();
//            Thread.sleep(10000);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            MyCompressorOutputStream s = new MyCompressorOutputStream(bos);
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
