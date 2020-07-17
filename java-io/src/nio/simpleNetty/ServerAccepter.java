package nio.simpleNetty;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Description: 连接接收器
 * @Author: zhangwei
 * @Date: 2020/7/17 17:09
 */
public class ServerAccepter implements Handler {
    private ServerSocketChannel key;

    private EventLoopGroup cGroup;

    ServerAccepter(EventLoopGroup group, ServerSocketChannel server){
        this.key = server;
        this.cGroup = group;
    }

    public void doRead(){
        //todo accept
        EventLoop eventLoop = cGroup.chooser();
        try {
            SocketChannel client = key.accept();
            client.configureBlocking(false);
            client.setOption(StandardSocketOptions.TCP_NODELAY ,true);
            final ClientReadHandler cHandler = new ClientReadHandler(client);
            eventLoop.execute(()->{
                try {
                    System.out.println("socket...send...to " + eventLoop.getName()+ " client port : " + client.socket().getPort());
                    client.register(eventLoop.getSelector(), SelectionKey.OP_READ, cHandler);
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
        } catch (IOException e){
            e.printStackTrace();
        }

    }


}
