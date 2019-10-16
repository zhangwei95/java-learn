package structure.proxy;

/**
 * 代理
 * 提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能
 */
public class Proxy implements Subject{
    private RealSubject realSubject;
    @Override
    public void request() {
        if(realSubject==null){
            realSubject=new RealSubject();
        }
        preRequest();
        realSubject.request();
        doRequest();
    }
    public void preRequest(){
        System.out.println("preRequest");
    }
    public void doRequest(){
        System.out.println("doRequest");
    }
}
