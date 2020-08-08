package com.zhangwei95.collection.list;

import org.junit.Test;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/21 19:53
 */
public class C02_ArrayList {
    static List arraylist = new ArrayList();

    static {
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);
        arraylist.add(4);
        arraylist.add(5);
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);
        arraylist.add(4);
        arraylist.add(5);
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);
        arraylist.add(4);
        arraylist.add(5);
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);
        arraylist.add(4);
        arraylist.add(5);
    }


    /**
     * 增强for循环 等价于 迭代器遍历   可以编译成字节码文件查看
     * @throw ConcurrentModifationExcetion
     */
    @Test
    public void testRemoveForeach(){
        for (Object o : arraylist) {
            arraylist.remove(o);
        }

    }

    /**
     * next() 会校验modCount 与 exceptModCount 是否一致
     * ArrayList.remove 会modCount++  expectedModCount不变
     * @throw ConcurrentModifationExcetion
     */
    @Test
    public void removeItr(){
        Iterator iterator = arraylist.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            arraylist.remove(next);
        }
    }


    @Test
    public void removeFor(){
        for (int i = 0; i < arraylist.size(); i++) {
            arraylist.remove(i);
            i--;
        }
    }

    /**
     * iterator.remove(); 实际上也是调用 ArrayList.remove
     * 然后会重置expectedModCount = modCount
     */
    @Test
    public void removeItrRemove(){
        Iterator iterator = arraylist.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            iterator.remove();
        }
    }

    /**
     * iterator.remove(); 实际上也是调用 ArrayList.remove
     * expectedModCount 线程私有  modCount修改后 expectedModCount不同步
     */
    @Test
    public void removeItrRemoveMultiThread() throws IOException {

        Thread t1 = new Thread(()->{
            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()){
                Object next = iterator.next();
                System.out.println(next);

            }
        });

        Thread t2 = new Thread(()->{
            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()){
                Object next = iterator.next();
                iterator.remove();
            }
        });
        t1.start();
        t2.start();
        System.in.read();
    }
}
