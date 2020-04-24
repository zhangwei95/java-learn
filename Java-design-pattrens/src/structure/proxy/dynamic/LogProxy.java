package structure.proxy.dynamic;

import structure.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogProxy implements InvocationHandler {

    private Subject s;

    public LogProxy(Subject s){
        this.s=s;
    }

    public void before(){
        System.out.println("log before");
    }

    public void after(){
        System.out.println("log after");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(s, args);
        after();
        return o;
    }
}
