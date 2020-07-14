package nio.netty;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;


/**
 * @Description: 接收处理器
 * @Author: zhangwei
 * @Date: 2020/7/14 09:42
 */
public class AcceptHandler extends ChannelInboundHandlerAdapter {

    private final EventLoopGroup selector;

    private final ChannelHandler handler;

    AcceptHandler(EventLoopGroup thread, ChannelHandler handler){
        this.selector = thread;
        this.handler = handler;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx){
        System.out.println("server registed....");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SocketChannel client = (SocketChannel) msg;

        ChannelPipeline pipeline = client.pipeline();

        pipeline.addLast(handler);

        selector.register(client);

    }


}
