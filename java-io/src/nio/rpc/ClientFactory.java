package nio.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 10:50
 */
public class ClientFactory {

    int poolSize = 1;

    Random rand = new Random();

    private ClientFactory(){}

    private static final ClientFactory FACTORY;

    static {
        FACTORY = new ClientFactory();
    }

    public static ClientFactory getInstance(){return FACTORY;}

    ConcurrentHashMap<InetSocketAddress,ClientPool> serverPool = new ConcurrentHashMap<>();

    public synchronized NioSocketChannel getClient(InetSocketAddress address){
        ClientPool clientPool = serverPool.get(address);

        if (clientPool == null){
            serverPool.putIfAbsent(address,new ClientPool(poolSize));
            clientPool = serverPool.get(address);
        }

        int i = rand.nextInt(poolSize);

        if (clientPool.clients[i] != null && clientPool.clients[i].isActive()){
            return clientPool.clients[i];
        }

        synchronized (clientPool.locks[i]){
            return clientPool.clients[i] = create(address);
        }



    }

    private NioSocketChannel create(InetSocketAddress address) {
        NioEventLoopGroup worker = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ClientResponseHandler());
                    }
                }).connect(address);


        try {
            NioSocketChannel client = (NioSocketChannel) connect.sync().channel();
            return client;
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return null;
    }


}
