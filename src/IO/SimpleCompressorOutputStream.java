package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream
    {
        private OutputStream out;

        public SimpleCompressorOutputStream(OutputStream out)
        {
            this.out = out;
        }

        @Override
        public void write(int b) throws IOException
        {

        }

        public void compress(byte[] b) throws IOException
            {
            String s;
            byte[] total = new byte[Byte.parseByte(String.valueOf(b[1]))];
            int p = 6, counter = 0, i = 0, first = (String.valueOf(b[6])).charAt(0);
            for(int j = 0; j<= Byte.parseByte(String.valueOf(b[0]), 2); j = j+7)
            {
                //convert the byte to string
                s = String.valueOf(b[p]);
                for (int f = 1; f<=s.length()-1;f++)
                {
                    //take each letter in s and convert to int it and add to maze
                    if(s.charAt(f) == s.charAt(f))
                    {
                        //in case they are the sa,e but no room left in the byte
                        if(counter == 255)
                        {
                            total[i] = (Byte.decode(String.valueOf(counter)));
                            counter = 0;
                            i++;
                            total[i] = (Byte.decode(String.valueOf(counter)));
                            i++;
                        }
                        counter++;
                    }
                        //in case they are different
                    else
                    {
                        total[i] = (Byte.decode(String.valueOf(counter)));
                        counter = 0;
                        i++;
                    }
                    j++;
                }
                p++;
                out.write(total);
                out.close();
            }
        }
    }
