package structure.proxy;

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
