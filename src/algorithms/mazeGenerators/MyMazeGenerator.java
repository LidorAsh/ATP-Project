package algorithms.mazeGenerators;


import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {

        int[][] maze;
        Random rand = new Random();
        Stack<Position> stack = new Stack<>();
        HashSet<Position> hashSet = new HashSet<>();
        Position end = new Position(rows - 2, columns - 1);

        maze = new int[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                maze[i][j] = 1;

        // Starting cells
        maze[1][0] = 0;
        maze[1][1] = 0;
        stack.push(new Position(1, 1));
        hashSet.add(new Position(1, 1));
        hashSet.add(new Position(1, 0));

        //　Allocate the maze using stack
        while (!stack.empty()) {
            Position current = stack.pop();
            int r = current.getRowIndex(), c = current.getColumnIndex();

            if (c == columns - 1)
                end = new Position(r, c);

            Position left = new Position(r, c - 2);
            Position right = new Position(r, c + 2);
            Position up = new Position(r - 2, c);
            Position down = new Position(r + 2, c);

            ArrayList<Integer> arr = new ArrayList<>();

            if (!hashSet.contains(up) && r - 2 >= 0) { arr.add(1); }
            if (!hashSet.contains(right) && c + 2 <= columns - 1) { arr.add(2); }
            if (!hashSet.contains(down) && r + 2 <= rows - 1) { arr.add(3); }
            if (!hashSet.contains(left) && c - 2 >= 0) { arr.add(4); }

            if (arr.size() > 0) {
                stack.push(current);
                int randIndex = rand.nextInt(arr.size());
                int nextCell = arr.remove(randIndex);

                switch (nextCell) {
                    case 1: // Up
                        //　Whether 2 cells up is out or not
                        if (maze[r - 2][c] != 0) {
                            maze[r - 2][c] = 0;
                            maze[r -1][c] = 0;
                            hashSet.add(up);
                            stack.push(up);
                        }
                        break;

                    case 2: // Right
                        // Whether 2 cells to the right is out or not
                        if (maze[r][c + 2] != 0) {
                            maze[r][c + 2] = 0;
                            maze[r][c+1] = 0;
                            hashSet.add(right);
                            stack.push(right);
                        }
                        break;

                    case 3: // Down
                        // Whether 2 cells down is out or not
                        if (maze[r + 2][c] != 0) {
                            maze[r + 2][c] = 0;
                            maze[r+1][c] = 0;
                            hashSet.add(down);
                            stack.push(down);
                        }
                        break;

                    case 4: // Left
                        // Whether 2 cells to the left is out or not
                        if (maze[r][c - 2] != 0) {
                            maze[r][c - 2] = 0;
                            maze[r][c-1] = 0;
                            hashSet.add(left);
                            stack.push(left);
                        }
                        break;
                }
            }
        }
        return new Maze(maze, new Position(1, 0), end);
    }
}
