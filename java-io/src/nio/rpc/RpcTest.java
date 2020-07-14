package nio.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 基于 netty 实现 rpc 调用
 *
 *     1，先假设一个需求，写一个RPC
 *     2，来回通信，连接数量，拆包？
 *     3，动态代理呀，序列化，协议封装
 *     4，连接池
 *     5，就像调用本地方法一样去调用远程的方法，面向java中就是所谓的 面向interface开发
 * @Author: zhangwei
 * @Date: 2020/7/14 10:16
 */
public class RpcTest {

    private static final String HOST ="192.168.153.1";

    private static final Integer PORT = 9090;

    @Test
    public void startServer() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(3);

        ServerBootstrap sbs = new ServerBootstrap();
        ChannelFuture bind = sbs.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("server accept client port: " + ch.remoteAddress().getPort());
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ServerRequestHandler());
                    }
                }).bind(new InetSocketAddress(HOST, PORT));

        try {
            bind.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void consumer(){
        int size = 20;
        Thread[] threads = new Thread[size];
        for (int i = 0; i <size; i++) {
            threads[i] = new Thread(()->{
                Order car = proxyGet(Order.class);//动态代理实现
                car.order("A10001");
            },"Thread" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

//        Order order = proxyGet(Order.class);
//        order.order("A10001");
//
//        Pay pay = proxyGet(Pay.class);
//        pay.pay("A10001",1000);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static <T> T proxyGet(Class<T> interfaceInfo) {
        ClassLoader classLoader = interfaceInfo.getClassLoader();

        Class<?>[] klass = {interfaceInfo};

        return (T) Proxy.newProxyInstance(classLoader, klass, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        // 1 调用 服务，方法，参数  ==》 封装成body [content]
                        String name = interfaceInfo.getName();
                        String methodName = method.getName();
                        Class<?>[] parameterTypes = method.getParameterTypes();

                        RpcBody body = new RpcBody();
                        body.setName(name);
                        body.setMethodName(methodName);
                        body.setParameterTypes(parameterTypes);
                        body.setArgs(args);

                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        ObjectOutputStream oout = new ObjectOutputStream(out);
                        oout.writeObject(body);
                        byte[] bodyBytes = out.toByteArray();

                        // 2 requestId + message  通信双方 协议约定   header + body  以及编码，解码问题
                        RpcHeader rpcHeader = generateHeader(bodyBytes);
                        out.reset();
                        oout = new ObjectOutputStream(out);
                        oout.writeObject(rpcHeader);
                        byte[] headerBytes = out.toByteArray();
                        // 3 连接池  获取连接
                        ClientFactory factory = ClientFactory.getInstance();
                        NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress(HOST, PORT));

                        // 4，发送--> 走IO  out -->走Netty（event 驱动）
                        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(headerBytes.length + bodyBytes.length);
                        CountDownLatch latch = new CountDownLatch(1);
                        long requestId = rpcHeader.getRequestId();

                        ResponseHandler.addCallBack(requestId, new Runnable() {
                            @Override
                            public void run() {
                                latch.countDown();
                            }
                        });
                        byteBuf.writeBytes(headerBytes);
                        byteBuf.writeBytes(bodyBytes);
                        ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
                        channelFuture.sync();  //io是双向的，你看似有个sync，她仅代表out
                        latch.await();
                        //5，？，如果从IO ，未来回来了，怎么将代码执行到这里（睡眠/回调，如何让线程停下来？你还能让他继续。。。）



                        return null;
                    }
                }

        );


    }

    public static RpcHeader generateHeader(byte[] bodyBytes){
        RpcHeader header = new RpcHeader();
        int size = bodyBytes.length;
        // 4字节标识符  后期扩展
        int f = 0x00000001;
        long requestId =  Math.abs(UUID.randomUUID().getLeastSignificantBits());
        header.setFlag(f);
        header.setDataLen(size);
        header.setRequestId(requestId);
        return header;
    }


}
