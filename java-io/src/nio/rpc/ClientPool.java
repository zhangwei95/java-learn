package nio.rpc;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Arrays;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 10:55
 */
public class ClientPool {

    NioSocketChannel[] clients;

    Object[] locks;

    ClientPool(int size){
        clients = new NioSocketChannel[size];
        locks = new Object[size];
        Arrays.fill(locks, new Object());
    }
}
