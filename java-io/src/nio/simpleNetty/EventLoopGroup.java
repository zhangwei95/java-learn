package nio.simpleNetty;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/17 15:12
 */
public class EventLoopGroup {

    private AtomicInteger cid = new AtomicInteger(0);

    private EventLoop[] children;

    EventLoopGroup(int nThread){
        children = new EventLoop[nThread];
        for (int i = 0; i < nThread;i++){
            children[i] = new EventLoop("T" + i);
        }
    }

    public EventLoop chooser(){
        return children[cid.getAndIncrement() % children.length];
    }

}
