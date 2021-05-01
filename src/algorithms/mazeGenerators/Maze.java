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

    public Maze(byte[] b) {
        int[][] maze = new int[Byte.parseByte(String.valueOf(b[0]), 2)][Byte.parseByte(String.valueOf(b[1]), 2)];
        int p = 6;
        String s;
        for(int i = 0; i<= Byte.parseByte(String.valueOf(b[0]), 2); i++)
        {
            for(int j = 0; j<= Byte.parseByte(String.valueOf(b[1])); j = j+7)
            {
                //convert the byte to string
                s = String.valueOf(b[p]);
                for (int f = 0; f<=s.length()-1;f++)
                    {
                        //take each letter in s and convert to int it and add to maze
                        maze[i][j] = Integer.parseInt(s.substring(f, f));
                        j++;
                    }
                p++;
            }
        }
        this.map = maze;
        //convert from byte to string to int
        this.start = new Position(Byte.parseByte(String.valueOf(b[2]), 2), Byte.parseByte(String.valueOf(b[3]), 2));
        this.goal = new Position(Byte.parseByte(String.valueOf(b[4]), 2), Byte.parseByte(String.valueOf(b[5]), 2));
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


    public byte[] toByteArray()
        {
            byte[] b = new byte[map.length* map[0].length + 6];
            int p = 6;
            StringBuilder s = new StringBuilder();
            for(int i = 0; i<= map.length; i++)
            {
                for(int j = 0; j<= map[0].length; j++)
                {
                    s.append(map[i][j]);
                }
            }
            for(int k = 0; k<= s.length(); k=k+7)
            {
                //convert to int and then to byte
                int temp = Byte.parseByte(s.substring(k,k+7), 2);
                b[p] = Byte.decode(String.valueOf(temp));
                p++;
            }
            //convert to byte and add to the array
            b[0] = (byte) map.length;
            b[1] = (byte) map[0].length;
            b[2] = (byte) start.getRowIndex();
            b[3] = (byte) start.getColumnIndex();
            b[4] = (byte) goal.getRowIndex();
            b[5] = (byte) goal.getColumnIndex();
            return b;
        }

}
