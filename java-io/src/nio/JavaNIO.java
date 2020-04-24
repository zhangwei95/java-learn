package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class JavaNIO {

    public static void main(String[] args) throws Exception {
//        nioRead();
        bufferProperty();
    }

    static void nioRead(){
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("D:\\gitRepository\\java-learn\\java-io\\src\\nio/read.txt", "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                int i=0;
                byte[] result = new byte[bytesRead];
                while (buf.hasRemaining()) {
                    result[i++] = buf.get();
                }
                System.out.print(new String(result, StandardCharsets.UTF_8));
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * buffer
     * capacity 缓冲区数组的总长度
     * position 下一个要操作的数据元素的位置
     * limit 缓冲区数组中不可操作的下一个元素的位置：limit<=capacity
     * mark 用于记录当前position的前一个位置或者默认是-1
     */
    static void bufferProperty() throws IOException {
        RandomAccessFile aFile = null;
        aFile = new RandomAccessFile("D:\\gitRepository\\java-learn\\java-io\\src\\nio/test.txt", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(10);
        byte[] fiveByte = {0,1,2,3,4};
        buf.put(fiveByte);
        buf.flip();
        channel.write(buf);
        buf.clear();
    }
}
