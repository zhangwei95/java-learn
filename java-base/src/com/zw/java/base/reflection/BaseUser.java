package com.zw.java.base.reflection;

public class BaseUser {
    static {
        System.out.println("BaseUser：静态代码块");
    }

    {
        System.out.println("BaseUser：动态代码块");
    }

    public BaseUser(){
        System.out.println("BaseUser：构造方法");
    }
}
