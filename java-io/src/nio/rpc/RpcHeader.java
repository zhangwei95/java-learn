package nio.rpc;

import java.io.Serializable;

/**
 * @Description: header
 * @Author: zhangwei
 * @Date: 2020/7/14 10:35
 */
public class RpcHeader implements Serializable {

    /**
     * 协议标志符 可以放很多信息 目前没用到
     */
    private int flag;
    /**
     * 请求id ,唯一
     */
    private long requestId;

    /**
     * 数据长度
     */
    private int dataLen;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }
}
