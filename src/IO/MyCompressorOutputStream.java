package IO;

import java.io.*;
import java.util.zip.DeflaterOutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private final OutputStream out;

    public MyCompressorOutputStream(OutputStream out)
    {
        this.out = out;
    }

    @Override
    public void write(int b) {
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        String mazeFileName = "savedMaze1.maze"; // temp file
        try {
            OutputStream outToSimple = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
            outToSimple.write(b); // use the simple compressor
            outToSimple.flush();

            InputStream inFromSimple = new FileInputStream(mazeFileName);
            byte[] bytes = inFromSimple.readAllBytes(); // get the compressed maze from the simple compressor

            DeflaterOutputStream defl = new DeflaterOutputStream(out); // stream which compress bytes
            defl.write(bytes);
            defl.flush();
            defl.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
