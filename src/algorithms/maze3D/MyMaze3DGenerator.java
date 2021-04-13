package algorithms.maze3D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{
    //generate a 3D maze
    @Override
    public Maze3D generate(int depth, int row, int column) throws IllegalArgumentException{
        if (depth < 2 || row < 2 || column < 2)//checks if the maze is legal
            throw new IllegalArgumentException("3D Maze size must be at least 2*2*2");

        int[][][] maze;
        Random rand = new Random();
        Stack<Position3D> toVisit = new Stack<>(); // this stack store positions to visit
        HashSet<Position3D> visited = new HashSet<>(); // // this hash set store the visited positions
        Position3D end = new Position3D(0, row - 2, column - 1);

        // start with maze full of walls
        maze = new int[depth][row][column];
        for (int z = 0; z < depth; z++) {
            for (int i = 0; i < row; i++)
                for (int j = 0; j < column; j++)
                    maze[z][i][j] = 1;
        }

        // initial cells
        maze[0][1][0] = 0;
        maze[0][1][1] = 0;
        toVisit.push(new Position3D(0, 1, 1));
        visited.add(new Position3D(0, 1, 1));
        visited.add(new Position3D(0, 1, 0));

        //　generate the maze using the stack
        while (!toVisit.empty()) {
            Position3D current = toVisit.pop();
            int d = current.getDepthIndex(), r = current.getRowIndex(), c = current.getColumnIndex();

            if (c == column - 1 && d == 0)
                end = new Position3D(d, r, c);

            // these are the neighbors of the current cell
            Position3D left = new Position3D(d, r, c - 2);
            Position3D right = new Position3D(d, r, c + 2);
            Position3D up = new Position3D(d, r - 2, c);
            Position3D down = new Position3D(d, r + 2, c);
            Position3D inside = new Position3D(d - 2, r, c);
            Position3D outside = new Position3D(d + 2, r, c);

            ArrayList<Integer> arr = new ArrayList<>();

            // check for any neighbor who has visited it in the past
            if (!visited.contains(up) && r - 2 >= 0) { arr.add(1); }
            if (!visited.contains(right) && c + 2 <= column - 1) { arr.add(2); }
            if (!visited.contains(down) && r + 2 <= row - 1) { arr.add(3); }
            if (!visited.contains(left) && c - 2 >= 0) { arr.add(4); }

            if (!visited.contains(inside) && d - 2 >= 0) { arr.add(5); }
            if (!visited.contains(outside) && d + 2 <= depth - 1) { arr.add(6); }

            if (arr.size() > 0) {
                toVisit.push(current);
                int randIndex = rand.nextInt(arr.size());
                int nextCell = arr.remove(randIndex);

                switch (nextCell) {
                    case 1: // Up
                        //　whether 2 cells up is out or not
                        if (maze[d][r - 2][c] != 0) {
                            maze[d][r - 2][c] = 0;
                            maze[d][r - 1][c] = 0;
                            visited.add(up);
                            toVisit.push(up);
                        }
                        break;

                    case 2: // Right
                        // whether 2 cells to the right is out or not
                        if (maze[d][r][c + 2] != 0) {
                            maze[d][r][c + 2] = 0;
                            maze[d][r][c + 1] = 0;
                            visited.add(right);
                            toVisit.push(right);
                        }
                        break;

                    case 3: // Down
                        // whether 2 cells down is out or not
                        if (maze[d][r + 2][c] != 0) {
                            maze[d][r + 2][c] = 0;
                            maze[d][r + 1][c] = 0;
                            visited.add(down);
                            toVisit.push(down);
                        }
                        break;

                    case 4: // Left
                        // whether 2 cells to the left is out or not
                        if (maze[d][r][c - 2] != 0) {
                            maze[d][r][c - 2] = 0;
                            maze[d][r][c - 1] = 0;
                            visited.add(left);
                            toVisit.push(left);
                        }
                        break;

                    case 5: // Inside
                        // whether 2 cells to the inside is out or not
                        if (maze[d - 2][r][c] != 0) {
                            maze[d - 2][r][c] = 0;
                            maze[d - 1][r][c] = 0;
                            visited.add(inside);
                            toVisit.push(inside);
                        }
                        break;


                    case 6: // Outside
                        // whether 2 cells to the outside is out or not
                        if (maze[d + 2][r][c] != 0) {
                            maze[d + 2][r][c] = 0;
                            maze[d + 1][r][c] = 0;
                            visited.add(outside);
                            toVisit.push(outside);
                        }
                        break;
                }
            }
        }
        return new Maze3D(new Position3D(0, 1, 0), end, maze); // the start position is constant while the end position is random
    }
}
