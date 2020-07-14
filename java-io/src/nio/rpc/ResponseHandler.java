package nio.rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 11:24
 */
public class ResponseHandler {
    private static ConcurrentHashMap<Long,Runnable> mapping = new ConcurrentHashMap<>();

    public static void addCallBack(long requestId,Runnable callback){
        mapping.putIfAbsent(requestId,callback);
    }

    public static void runCallback(long requestId){
        Runnable callback = mapping.get(requestId);
        callback.run();
    }

    private static void removeCallback(long requestId) {
        mapping.remove(requestId);
    }

}
