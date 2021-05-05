package IO;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.JarOutputStream;

public class SimpleCompressorOutputStream extends OutputStream
{
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out)
    {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException
    {

    }

    public void write(byte[] b) throws IOException
    {
        ArrayList<Integer> arr = new ArrayList<>();
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();

            Maze maze = (Maze) o;
            int sizeY = maze.getYMazeLength();
            int sizeX = maze.getXMazeLength();
            int startRow = maze.getStartPosition().getRowIndex();
            int startCOl = maze.getStartPosition().getColumnIndex();
            int goalRow = maze.getGoalPosition().getRowIndex();
            int goalCol = maze.getGoalPosition().getColumnIndex();
            int [][] mazeMap = maze.getMap();

            while(sizeY > 127) { // height of maze
                arr.add(127);
                sizeY -= 127;
            }
            arr.add(sizeY);
            arr.add(-1);

            while(sizeX > 127) { // width of maze
                arr.add(127);
                sizeX -= 127;
            }
            arr.add(sizeX);
            arr.add(-1);

            while(startRow > 127) { // start - row index
                arr.add(127);
                startRow -= 127;
            }
            arr.add(startRow);
            arr.add(-1);

            while(startCOl > 127) { // start - column index
                arr.add(127);
                startCOl -= 127;
            }
            arr.add(startCOl);
            arr.add(-1);

            while(goalRow > 127) { // goal - row index
                arr.add(127);
                goalRow -= 127;
            }
            arr.add(goalRow);
            arr.add(-1);

            while(goalCol > 127) { // goal - column index
                arr.add(127);
                goalCol -= 127;
            }
            arr.add(goalCol);
            arr.add(-1);

            int last = mazeMap[0][0], counter = 0;
            boolean flag = false;

            for(int i = 0; i < maze.getYMazeLength(); i ++) {
                for (int j = 0; j < maze.getXMazeLength(); j++) {

                    if (mazeMap[i][j] == last) {
                        if(flag){
                            arr.add(0);
                            flag= false;
                        }
                        counter++;
                    }
                    else { //if (mazeMap[i][j] != last)
                        arr.add(counter);
                        counter = 1;
                        last = mazeMap[i][j];
                    }

                    if (counter == 127){
                        arr.add(counter);
                        counter = 0;
                        flag = true;
                    }
                }
            }

            arr.add(counter);

            byte[] bytes = new byte[arr.size()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = arr.get(i).byteValue();
            }
            out.write(bytes);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

    }
}