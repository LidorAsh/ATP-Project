package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D {

    private final int[][][] map;
    private final Position3D start;
    private final Position3D goal;

    public Maze3D(Position3D start, Position3D goal, int[][][] map) {
        this.map = map;
        this.start = start;
        this.goal = goal;
    }

    public int[][][] getMap() {
        return this.map;
    }

    public Position3D getStartPosition() {
        return this.start;
    }

    public Position3D getGoalPosition(){
        return this.goal;
    }

    public int getXMazeLength() {return  this.map[0][0].length;}

    public int getYMazeLength() {return  this.map[0].length;}

    public int getZMazeLength() {return  this.map.length;}

    /**
     * Method which print the maze in the requested format
     */
    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < map.length; depth++){
            for(int row = 0; row < map[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < map[0][0].length; col++) {
                    Position3D p = new Position3D(depth, row, col);
                    if (p.equals(this.start)) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (p.equals(this.goal)) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(map[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < map.length - 1) {
                System.out.print("---");
                for (int i = 0; i < map[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }
}
