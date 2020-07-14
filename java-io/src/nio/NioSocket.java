package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: NIO Socket
 * @Author: zhangwei
 * @Date: 2020/7/12 20:11
 */
public class NioSocket {

    public static void main(String[] args) throws IOException {

        List<SocketChannel> clients = new ArrayList<>();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        // 非阻塞
        serverSocketChannel.configureBlocking(false);


        while (true){

            SocketChannel client = serverSocketChannel.accept();


            if (client == null){
                System.out.println("no client connect");
            } else {

                client.configureBlocking(false);
                int port = client.socket().getPort();
                SocketAddress remoteSocketAddress = client.getRemoteAddress();
                System.out.println("client..."+ remoteSocketAddress +"  port: " + port);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);  //可以在堆里   堆外

            for (SocketChannel channel:clients){
                int read = channel.read(buffer);
                if (read >0 ){
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);

                    String b = new String(aaa);
                    System.out.println(channel.socket().getPort() + " : " + b);
                    buffer.clear();
                }
            }
        }



    }


}
