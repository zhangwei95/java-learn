package nio.simpleNetty;


import io.netty.bootstrap.ServerBootstrap;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/17 15:13
 */
public class ServerBootStrap {

    private EventLoopGroup group;

    private EventLoopGroup childGroup;

    private ServerAccepter accepter;


    public ServerBootStrap group(EventLoopGroup boss, EventLoopGroup worker){
        this.group = boss;
        this.childGroup = worker;
        return this;
    }


    public void bind(int port) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(port));
        accepter = new ServerAccepter(childGroup,server);

        EventLoop eventLoop = group.chooser();
        eventLoop.execute(()->{

            try {
                eventLoop.setName(Thread.currentThread() + eventLoop.getName());
                System.out.println("bind...server...to " + eventLoop.getName());
                server.register(eventLoop.getSelector(), SelectionKey.OP_ACCEPT, accepter);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }

        });

    }


}
