package nio.simpleNetty;

import java.io.IOException;

/**
 * @Description: 简单netty
 * @Author: zhangwei
 * @Date: 2020/7/17 15:09
 */
public class SimpleNetty {

    public static void main(String[] args) throws IOException {
        EventLoopGroup boss = new EventLoopGroup(1);

        EventLoopGroup worker = new EventLoopGroup(3);

        ServerBootStrap bootStrap = new ServerBootStrap();

        bootStrap.group(boss,worker).bind(9090);


        System.in.read();

    }



}
