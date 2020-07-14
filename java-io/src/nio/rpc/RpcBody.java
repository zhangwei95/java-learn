package nio.rpc;

import java.io.Serializable;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/14 10:36
 */
public class RpcBody implements Serializable {
    private String name;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] args;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
