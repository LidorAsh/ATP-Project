package algorithms.mazeGenerators;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;



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
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        try (ObjectInputStream in = new ObjectInputStream(bis)) {

            Object o = in.readObject();
            Maze maze = (Maze) o;
            this.map = maze.getMap();
            this.start = maze.getStartPosition();
            this.goal = maze.getGoalPosition();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out;
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            return bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // ignore close exception
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze = (Maze) o;
        return Arrays.deepEquals(map, maze.map) && Objects.equals(start, maze.start) && Objects.equals(goal, maze.goal);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(start, goal);
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }
}