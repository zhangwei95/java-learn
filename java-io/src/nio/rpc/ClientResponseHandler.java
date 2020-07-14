package nio.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @Description: 客户端发起请求后 返回结果处理器
 * @Author: zhangwei
 * @Date: 2020/7/14 11:15
 */
public class ClientResponseHandler extends ChannelInboundHandlerAdapter {
    private static final Integer LEAST_LENGTH = 128;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        if (buf.readableBytes() > LEAST_LENGTH){
            byte[] bytes = new byte[LEAST_LENGTH];
            buf.readBytes(bytes);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(in);
            RpcHeader header = (RpcHeader) oin.readObject();
            System.out.println("client response @ id: "+ header.getRequestId());
            //TODO:
            ResponseHandler.runCallback(header.getRequestId());
        }

        super.channelRead(ctx, msg);
    }
}
