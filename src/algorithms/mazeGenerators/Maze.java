package algorithms.mazeGenerators;

/**
 * This class represent a maze
 */
public class Maze {

    /* the variables are final because we don't change their content after the generation */
    private final int[][] map;
    private final Position start;
    private final Position goal;


    public Maze(int[][] maze, Position start, Position goal) {
        this.map = maze;
        this.start = start;
        this.goal = goal;
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


}
