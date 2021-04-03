package algorithms.mazeGenerators;

public class Maze {
    private int[][] map;
    private Position start;
    private Position goal;

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
