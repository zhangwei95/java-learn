package nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 09:52
 */
public class InHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        CharSequence str = buf.getCharSequence(0,buf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(str);
        ctx.writeAndFlush(buf);
    }
}
