package IO;

import java.io.*;
import java.util.zip.InflaterInputStream;
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

    @Override
    public int read(byte b[]) throws IOException {
        try {

//            System.out.println(in.available());

            InflaterInputStream infl = new InflaterInputStream(in);
            byte[] bytes = infl.readAllBytes();
//            System.out.println(in.available());



            InputStream inputStream = new SimpleDecompressorInputStream(new ByteArrayInputStream(bytes));
            inputStream.read(b);






            //ByteArrayInputStream out = new ByteArrayInputStream(in);
//            InputStream inputStream = new SimpleDecompressorInputStream(new InflaterInputStream(in));

            //byte[] tempBytes = new byte[infl.available()];
            //infl.read(tempBytes);










        } catch (Exception e) {
            e.printStackTrace();

        }

        return 0;
    }
}
