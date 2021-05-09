package IO;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.io.*;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream
{
    private final InputStream in;

    public SimpleDecompressorInputStream(InputStream in)
    {
        this.in = in;
    }

    @Override
    public int read() {
        return 0;
    }

    @Override
    public int read(byte[] b) {
        try {
            byte[] tempBytes = new byte[in.available()]; // get all the bytes available
            in.read(tempBytes);

            ArrayList<Integer> arr = new ArrayList<>();

            int[][] mazeMap = null;
            int sum = 0;
            int counter = 0;
            int index = 0;
            for (int i = 0; i < tempBytes.length; i++) {
                if (counter < 6) {
                    while (tempBytes[i] != -1) {
                        sum += tempBytes[i];
                        i++;
                    }
                    arr.add(sum);
                    sum = 0;
                    counter++;
                    if (counter == 5)
                        mazeMap = new int[arr.get(0)][arr.get(1)];
                } else {
                    index = i;
                    break;
                }
            }

            int turn = 1;
            int x = 0, y = 0;
            for (int i = index; i < tempBytes.length; i++) {
                sum = tempBytes[i];
                while (sum > 0) {
                    mazeMap[y][x] = turn;
                    x++;

                    if (x == mazeMap[0].length) {
                        x = 0;
                        y++;
                    }
                    sum--;
                }

                if (turn == 1)
                    turn = 0;
                else
                    turn = 1;
            }

            Position start = new Position(arr.get(2), arr.get(3));
            Position goal = new Position(arr.get(4), arr.get(5));

            Maze maze = new Maze(mazeMap, start, goal);

            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                ObjectOutputStream out;
                out = new ObjectOutputStream(bos);
                out.writeObject(maze);
                out.flush();
                tempBytes = bos.toByteArray();

                ByteArrayInputStream bis = new ByteArrayInputStream(tempBytes);
                bis.read(b);

                return tempBytes.length;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}