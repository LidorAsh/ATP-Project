package algorithms.mazeGenerators;

import static java.lang.Byte.*;

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
        /*if((parseByte(String.valueOf(b[0])) == 0) || (parseByte(String.valueOf(b[1])) == 0))
            { System.exit(0);}
        */int[][] maze = new int[parseByte(String.valueOf(b[0]), 2)][parseByte(String.valueOf(b[1]), 2)];
        int p = b[5];
        String s;
        for(int i = 0; i<= parseByte(String.valueOf(b[0]), 2); i++)
        {
            for(int j = 0; j<= parseByte(String.valueOf(b[1])); j = j+7)
            {
                //convert the byte to string
                s = String.valueOf(b[p]);
                for (int f = 0; f<s.length()-1;f++)
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
        this.start = new Position(parseByte(String.valueOf(b[2]), 2), parseByte(String.valueOf(b[3]), 2));
        this.goal = new Position(parseByte(String.valueOf(b[4]), 2), parseByte(String.valueOf(b[5]), 2));
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
            byte[] b = new byte[(map.length) * (map[0].length) * 2];
            //convert to byte and add to the array
            b[0] = (byte) locate(b, map.length, 6);
            b[1] = (byte) (locate(b, map[0].length, b[0] + 6) + b[0]);
            b[2] = (byte) (locate(b, start.getRowIndex(), b[1] + 6) + b[1]);
            b[3] = (byte) (locate(b, start.getColumnIndex(), b[2] + 6) + b[2]);
            b[4] = (byte) (locate(b, goal.getRowIndex(), b[3] + 6) + b[3]);
            b[5] = (byte) (locate(b, goal.getColumnIndex(), b[4] + 6) + b[4]);
            int p = b[5] + 6;
            StringBuilder s = new StringBuilder();
            for (int[] ints : map)
                {
                    for (int j = 0; j < map[0].length; j++)
                        {
                            s.append(ints[j]);
                        }
                }
            for(int k = 0; k< s.length(); k=k+8)
            {
                //convert from string to byte
                //int temp = Integer.parseInt(s.substring(k,k+8), 2);
                b[p] = Byte.parseByte(s.substring(k, k + 7), 2);
                p++;
            }

            return b;
        }
    public int locate(byte []b, int represenetive, int write)
        {
            int maxVal = 0, startIndex = 0, count = 0, index = 7;
            while(maxVal<represenetive)
            {
                count++;
                maxVal += MAX_VALUE;
            }
            String s = Integer.toBinaryString(represenetive);
            //Byte bit = valueOf(s, 10);
            if(s.length()%8 != 0)
                {
                    while (s.length()%8 != 0)
                    s = "0" + s;
                }
            //for(int k = 0;k<count; k++)
            //    s = "0000000" + s;
            //s = s + String.valueOf(represenetive);
            for(int l = 0; l<count; l++)
            {
                if(startIndex == s.length())
                    break;
                b[count + write] = Byte.parseByte(s.substring(startIndex, index), 2);
                startIndex = index+1;
                index = index+8;
            }
            return count+1;
        }
}
