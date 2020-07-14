package nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author:
 * @create: 2020-05-17 16:18
 */
public class SocketClient {

    public static void main(String[] args) {
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("192.168.153.1", 9090));

            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while (true) {
                if (socketChannel.finishConnect()) {
//                    int read = socketChannel.read(readBuffer);
//                    if (read <= 0){
                        String line = reader.readLine();
                        if (line != null) {
                            byte[] bb = line.getBytes();
                            writeBuffer.put(bb);
                            writeBuffer.flip();
                            socketChannel.write(writeBuffer);
                            writeBuffer.clear();
                        }
//                    }

//                    while (read > 0) {
//                        readBuffer.flip();
//                        if (readBuffer.hasRemaining()) {
//                            byte[] dd = new byte[readBuffer.limit()];
//                            readBuffer.get(dd);
//                            System.out.println(new String(dd));
//                        }
//                        readBuffer.clear();
//                        read = socketChannel.read(readBuffer);
//                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
