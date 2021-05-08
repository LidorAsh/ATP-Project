package IO;

import algorithms.maze3D.Position3D;

import java.io.*;
import java.util.Arrays;
import java.util.zip.DeflaterOutputStream;

public class MyCompressorOutputStream extends OutputStream
    {
        private OutputStream out;

        public MyCompressorOutputStream(OutputStream out)
        {
            this.out = out;
        }

        @Override
        public void write(int b) throws IOException {

        }

        @Override
        public void write(byte[] b) throws IOException
        {
            String mazeFileName = "savedMaze1.maze";
            try {



                OutputStream outToSimple = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
                outToSimple.write(b);
                outToSimple.flush();


                InputStream inFromSimple = new FileInputStream(mazeFileName);
                byte[] bytes = inFromSimple.readAllBytes();
//                System.out.println(Arrays.toString(bytes));

//                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);


                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DeflaterOutputStream defl = new DeflaterOutputStream(out);
                defl.write(bytes);
                defl.flush();
                defl.close();


                //out.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            /*String inFile = b[0];
            //String outFile = b[1];
            int bufSize = 16;
            byte[] buffer = new byte[bufSize];
            String crlf = System.getProperty("line.separator");
            try {
                FileInputStream in = new FileInputStream(inFile);
                OutputStreamWriter out = new OutputStreamWriter(
                        new FileOutputStream(outFile));
                int n = in.read(buffer,0,bufSize);
                String s = null;
                int count = 0;
                while (n!=-1) {
                    count += n;
                    s = bytesToHex(buffer,0,n);
                    out.write(s);
                    out.write(crlf);
                    n = in.read(buffer,0,bufSize);
                }

                in.close();
                out.close();
                System.out.println("Number of input bytes: "+count);

            } catch (IOException e) {
                System.out.println(e.toString());
            }*/
        }/*
            public String bytesToHex(byte[] b, int off, int len)
            {
                StringBuffer buf = new StringBuffer();
                for (int j=0; j<b.length; j=j+8)
                    buf.append(byteToHex(b[off+j]));
                return buf.toString();
            }
            public String byteToHex(byte b)
            {
                char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7',
                        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] a = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
                return new String(a);
            }*/

        }
    }
