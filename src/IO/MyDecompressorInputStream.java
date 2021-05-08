package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterOutputStream;

public class MyDecompressorInputStream extends InputStream
{
    private InputStream in;

    public MyDecompressorInputStream(InputStream in)
    {
        this.in = in;
    }

    @Override
    public int read() throws IOException
    {
        return 0;
    }

    public static byte[] decompress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out);
            infl.write(in);
            infl.flush();
            infl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
