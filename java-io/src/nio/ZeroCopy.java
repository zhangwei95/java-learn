package nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @Description: zeroCopy
 * @Author: zhangwei
 * @Date: 2020/7/15 16:00
 */
public class ZeroCopy {


    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\57415\\Desktop\\zeroCopy.txt");
        long length = file.length();
        byte[] ds = new byte[(int)length];
        MappedByteBuffer mappedByteBuffer
                = new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY,0,length);

        for (int offset = 0; offset < length;offset++){

            byte b = mappedByteBuffer.get();
            ds[offset] = b;
        }
        Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
        while (scan.hasNext()){
            System.out.println(scan.next() + " ");
        }


    }
}
