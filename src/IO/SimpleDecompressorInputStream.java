package IO;

import algorithms.mazeGenerators.Position;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SimpleDecompressorInputStream extends InputStream
    {
        private InputStream in;

        public SimpleDecompressorInputStream(InputStream in)
        {
            this.in = in;
        }

        @Override
        public int read() throws IOException
        {
            return 0;
        }

        @Override
        public int read(byte[] b) throws IOException
        {
            if (b.length == 0)
            {
                return 0;
            }

            int c = read();
            if (c == -1)
            {
                return -1;
            }

            int d = read();
            int[][] maze = new int[c][d];
            int startX = read();
            int startY = read();
            Position start = new Position(startX,startY);
            int goalX = read();
            int goalY = read();
            String s;
            Position goal = new Position(goalX,goalY);
            try
            {
                for (int i = 1; i < maze.length ; i++)
                {
                    int p = 0;
                    for(int j = 0; j<maze[0].length;j++)
                    {
                        c = read();
                        if (c == -1) {
                            break;
                        }
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
            } catch (IOException ignored) {}
            return 0;
        }
    }
