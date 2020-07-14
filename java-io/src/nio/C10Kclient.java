package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Random;


public class C10Kclient {

    public static void main(String[] args) {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress serverAddr = new InetSocketAddress("192.168.153.1", 9090);

        //端口号的问题：65535
        //  windows
        for (int i = 10000; i < 10100; i++) {
            try {
                SocketChannel client1 = SocketChannel.open();
                Thread.sleep(new Random().nextInt(1) * 1000);
//                SocketChannel client2 = SocketChannel.open();
                /*
                linux中你看到的连接就是：
                client...port: 10508
                client...port: 10508
                 */
                client1.bind(new InetSocketAddress("192.168.153.1", i));
                //  192.168.150.1：10000   192.168.150.11：9090
                client1.connect(serverAddr);
                boolean c1 = client1.isOpen();
                clients.add(client1);
//                client2.bind(new InetSocketAddress("192.168.110.100", i));
//                //  192.168.110.100：10000  192.168.150.11：9090
//                client2.connect(serverAddr);
//                boolean c2 = client2.isOpen();
//                clients.add(client2);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        }
        System.out.println("clients "+ clients.size());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
