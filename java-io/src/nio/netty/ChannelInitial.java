package nio.netty;

import io.netty.channel.*;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 09:57
 */
@ChannelHandler.Sharable
public abstract class ChannelInitial<C extends Channel> extends ChannelInboundHandlerAdapter {

    protected abstract void initChannel(C channel) throws Exception;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        initChannel((C)ctx.channel());
        ctx.pipeline().remove(this);
    }



}
