package structure.proxy.dynamic;

import structure.proxy.Subject;

public class DynamicProxy implements Subject {

    @Override
    public void request() {
        System.out.println("DynamicProxy request!!!1");
    }
}
