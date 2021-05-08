package algorithms.mazeGenerators;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Byte.*;

/**
 * This class represent a maze
 */
public class Maze implements Serializable {

    /* the variables are final because we don't change their content after the generation */
    private int[][] map;
    private Position start;
    private Position goal;

    public Maze(int[][] maze, Position start, Position goal) {
        this.map = maze;
        this.start = start;
        this.goal = goal;
    }

    public Maze(byte[] b) {
        //System.out.println(Arrays.toString(b)); ////////////////////////
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        ObjectInputStream in = null;
        try {

            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            Maze maze = (Maze) o;
            this.map = maze.getMap();
            this.start = maze.getStartPosition();
            this.goal = maze.getGoalPosition();

        } catch (IOException e) {
            e.printStackTrace();
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

    public Position getStartPosition() {
        return this.start;
    }

    public Position getGoalPosition() {
        return this.goal;
    }

    public int getXMazeLength() {return  this.map[0].length;}

    public int getYMazeLength() {return  this.map.length;}

    public int[][] getMap() {
        return map;
    }

    /**
     * Method which print the maze in the requested format
     */
    public void print(){
        for (int i = 0; i < map.length; i++){
            System.out.print("{ ");
            for (int j = 0; j < map[0].length; j++){
                Position p = new Position(i, j);
                if (p.equals(this.start))
                    System.out.print("S ");
                else if (p.equals(this.goal))
                    System.out.print("E ");
                else
                    System.out.print(this.map[i][j] + " ");
            }
            System.out.println("}");
        }
    }


    public byte[] toByteArray() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            byte[] yourBytes = bos.toByteArray();
            return yourBytes;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return null;
    }

//    public int locate(byte []b, int represenetive, int write)
//    {
//        int maxVal = 0, startIndex = 0, count = 0, index = 7;
//        while(maxVal<represenetive)
//        {
//            count++;
//            maxVal += MAX_VALUE;
//        }
//        String s = Integer.toBinaryString(represenetive);
//        //Byte bit = valueOf(s, 10);
//        if(s.length()%8 != 0)
//        {
//            while (s.length()%8 != 0)
//                s = "0" + s;
//        }
//        //for(int k = 0;k<count; k++)
//        //    s = "0000000" + s;
//        //s = s + String.valueOf(represenetive);
//        for(int l = 0; l<count; l++)
//        {
//            if(startIndex == s.length())
//                break;
//            System.out.println(startIndex);
//            b[count + write] = Byte.parseByte(s.substring(startIndex, index), 2);
//            startIndex = index+1;
//            index = index+8;
//        }
//        return 1;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze = (Maze) o;
        return Arrays.equals(map, maze.map) && Objects.equals(start, maze.start) && Objects.equals(goal, maze.goal);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(start, goal);
        result = 31 * result + Arrays.hashCode(map);
        return result;
    }
}