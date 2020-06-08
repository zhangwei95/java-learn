package com.zw.java.base.reflection;

public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException {
        test1();
//        test2();
//        test3();
    }

    public static void test1(){
        Class<?> clz = BaseUser.class;
    }

    public static void test2() throws ClassNotFoundException {
        Class<?> clz = Class.forName("com.zw.java.base.reflection.BaseUser");
    }

    public static void test3(){
        Class<?> clz = new BaseUser().getClass();
    }
}
