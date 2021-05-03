package Server;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.nio.channels.Channels;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            int[] mazeSize = (int[]) fromClient.readObject();
            IMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeSize[0], mazeSize[1]);

            // TODO - compress the maze



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




        // The Streams from Channels are interruptible,
        // so we decorate our input stream even more to enable it to also be interruptible:
        InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(interruptibleInputStream));
        BufferedWriter toClient = new BufferedWriter(new PrintWriter(outToClient));

        String clientCommand;

        try {
            while (fromClient != null && !(clientCommand = fromClient.readLine()).toLowerCase().equals("exit")) {
                Thread.sleep(2000);
                String reversed = new StringBuilder(clientCommand).reverse().toString();
                toClient.write(reversed + "\n");
                toClient.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // if the current Thread is interrupted, we will exit the strategy
            // (our thread pool is shut down and terminated the running threads)
            e.printStackTrace();
        }

    }
}
