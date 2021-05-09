package IO;

import java.io.*;
import java.util.zip.InflaterInputStream;

public class MyDecompressorInputStream extends InputStream {
    private final InputStream in;

    public MyDecompressorInputStream(InputStream in)
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
            InflaterInputStream infl = new InflaterInputStream(in); // decompress the maze
            byte[] bytes = infl.readAllBytes();

            InputStream inputStream = new SimpleDecompressorInputStream(new ByteArrayInputStream(bytes)); // use the simple decompressor
            return inputStream.read(b);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
