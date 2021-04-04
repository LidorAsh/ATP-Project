package algorithms.mazeGenerators;

import java.util.*;

/**
 * This class represent a complex maze
 */
public class MyMazeGenerator extends AMazeGenerator{

    /**
     * @param rows - which represent the height of the maze
     * @param columns - which represent the width of the maze
     * @return a maze (the algorithm is the iterative DFS)
     */
    @Override
    public Maze generate(int rows, int columns) {

        int[][] maze;
        Random rand = new Random();
        Stack<Position> toVisit = new Stack<>(); // this stack store positions to visit
        HashSet<Position> visited = new HashSet<>(); // // this hash set store the visited positions
        Position end = new Position(rows - 2, columns - 1);

        maze = new int[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                maze[i][j] = 1; // start with maze full of walls

        // initial cells
        maze[1][0] = 0;
        maze[1][1] = 0;
        toVisit.push(new Position(1, 1));
        visited.add(new Position(1, 1));
        visited.add(new Position(1, 0));

        //　generate the maze using the stack
        while (!toVisit.empty()) {
            Position current = toVisit.pop();
            int r = current.getRowIndex(), c = current.getColumnIndex();

            if (c == columns - 1)
                end = new Position(r, c);

            // these are the neighbors of the current cell
            Position left = new Position(r, c - 2);
            Position right = new Position(r, c + 2);
            Position up = new Position(r - 2, c);
            Position down = new Position(r + 2, c);

            ArrayList<Integer> arr = new ArrayList<>();

            // check for any neighbor who has visited it in the past
            if (!visited.contains(up) && r - 2 >= 0) { arr.add(1); }
            if (!visited.contains(right) && c + 2 <= columns - 1) { arr.add(2); }
            if (!visited.contains(down) && r + 2 <= rows - 1) { arr.add(3); }
            if (!visited.contains(left) && c - 2 >= 0) { arr.add(4); }

            if (arr.size() > 0) {
                toVisit.push(current);
                int randIndex = rand.nextInt(arr.size());
                int nextCell = arr.remove(randIndex);

                switch (nextCell) {
                    case 1: // Up
                        //　whether 2 cells up is out or not
                        if (maze[r - 2][c] != 0) {
                            maze[r - 2][c] = 0;
                            maze[r -1][c] = 0;
                            visited.add(up);
                            toVisit.push(up);
                        }
                        break;

                    case 2: // Right
                        // whether 2 cells to the right is out or not
                        if (maze[r][c + 2] != 0) {
                            maze[r][c + 2] = 0;
                            maze[r][c+1] = 0;
                            visited.add(right);
                            toVisit.push(right);
                        }
                        break;

                    case 3: // Down
                        // whether 2 cells down is out or not
                        if (maze[r + 2][c] != 0) {
                            maze[r + 2][c] = 0;
                            maze[r+1][c] = 0;
                            visited.add(down);
                            toVisit.push(down);
                        }
                        break;

                    case 4: // Left
                        // whether 2 cells to the left is out or not
                        if (maze[r][c - 2] != 0) {
                            maze[r][c - 2] = 0;
                            maze[r][c-1] = 0;
                            visited.add(left);
                            toVisit.push(left);
                        }
                        break;
                }
            }
        }
        return new Maze(maze, new Position(1, 0), end); // the start position is constant while the end position is random
    }
}
