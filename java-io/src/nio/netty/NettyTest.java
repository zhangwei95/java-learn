package nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 09:08
 */
public class NettyTest {


    @Test
    public void byteBuf(){
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.heapBuffer(8, 16);
        print(byteBuf);

        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);

    }

    private void print(ByteBuf byteBuf){
        System.out.println("isReadable: " +  byteBuf.isReadable());
        System.out.println("readerIndex: " +  byteBuf.readerIndex());
        System.out.println("readableBytes: " +  byteBuf.readableBytes());
        System.out.println("isWritable: " +  byteBuf.isWritable());
        System.out.println("writerIndex: " +  byteBuf.writerIndex());
        System.out.println("writableBytes: " +  byteBuf.writableBytes());
        System.out.println("capacity: " +  byteBuf.capacity());
        System.out.println("maxCapacity: " +  byteBuf.maxCapacity());
        System.out.println("isDirect: " +  byteBuf.isDirect());
        System.out.println("------------------------");
    }


    @Test
    public void loopExecutor() throws IOException {
        NioEventLoopGroup select = new NioEventLoopGroup(2);

        select.execute(()->{
            for(;;){
                System.out.println("hello world001");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        select.execute(()->{
            for(;;){
                System.out.println("hello world002");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        System.in.read();
    }


    @Test
    public void clientMode() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);

        NioSocketChannel client = new NioSocketChannel();

        thread.register(client);

        ChannelPipeline pipeline = client.pipeline();
        pipeline.addLast();

        ChannelFuture connect = client.connect(new InetSocketAddress("192.168.153.2", 9090));
        ChannelFuture sync = connect.sync();

        ByteBuf buf = Unpooled.copiedBuffer("hello netty".getBytes());

        ChannelFuture sendFuture = client.writeAndFlush(buf);

        sendFuture.sync();


        sync.channel().closeFuture().sync();

        System.out.println("client over....");
    }



    @Test
    public void nettyClient() throws InterruptedException {
        NioEventLoopGroup threadGroup = new NioEventLoopGroup(1);

        Bootstrap bs = new Bootstrap();

        ChannelFuture connect = bs.group(threadGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast();
                    }
                }).connect(new InetSocketAddress("192.168.153.1", 9090));

        Channel client = connect.sync().channel();

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello , im netty client".getBytes());

        ChannelFuture sendFuture = client.writeAndFlush(byteBuf);
        sendFuture.sync();

        client.closeFuture().sync();
    }


    @Test
    public void serverMode() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);

        NioServerSocketChannel server = new NioServerSocketChannel();

        thread.register(server);

        ChannelPipeline pipeline = server.pipeline();

        pipeline.addLast(new AcceptHandler(thread, new ChannelInitial<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                p.addLast(new InHandler());
            }
        }));

        ChannelFuture bind = server.bind(new InetSocketAddress("192.168.153.1", 9090));

        bind.sync().channel().closeFuture().sync();

        System.out.println("server close....");

    }


    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(3);
        ServerBootstrap sbs = new ServerBootstrap();

        ChannelFuture bind = sbs.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitial<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new InHandler());
                    }
                }).bind(new InetSocketAddress("192.168.153.1", 9090));

        bind.sync().channel().closeFuture().sync();

    }


}
