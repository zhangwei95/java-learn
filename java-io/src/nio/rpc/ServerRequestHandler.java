package nio.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 11:42
 */
public class ServerRequestHandler extends ChannelInboundHandlerAdapter {

    private static final Integer LEAST_LENGTH = 84;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        ByteBuf sendBuf = buf.copy();

        if (buf.readableBytes() > LEAST_LENGTH) {
            byte[] bytes = new byte[LEAST_LENGTH];
            buf.readBytes(bytes);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(in);

            RpcHeader header = (RpcHeader) oin.readObject();


            System.out.println("server response @ id: " + header.getRequestId());

            if (buf.readableBytes() >= header.getDataLen()) {
                byte[] data = new byte[header.getDataLen()];
                buf.readBytes(data);

                ByteArrayInputStream dbain = new ByteArrayInputStream(data);
                ObjectInputStream doin = new ObjectInputStream(dbain);
                RpcBody rpcBody = (RpcBody) doin.readObject();
                System.out.println("interface :" + rpcBody.getName() + "  method:" + rpcBody.getMethodName()
                        + "  args:" + Arrays.toString(rpcBody.getArgs()));
            }

        }

        ChannelFuture channelFuture = ctx.writeAndFlush(sendBuf);
        channelFuture.sync();
    }


}
