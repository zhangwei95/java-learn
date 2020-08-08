package com.zhangwei95.collection.list;

import org.junit.Test;

import java.util.List;
import java.util.Vector;

/**
 * @Description: Vector
 * 数据结构和ArrayList相同
 * 特性也相同
 *
 * 区别  同步容器
 * 所有方法 带了同步关键字
 * @Author: zhangwei
 * @Date: 2020/7/21 19:53
 */
public class C01_Vector {
    static List<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add(String.format("票%d", i));
        }
    }

    @Test
    public void vectorT1(){

    }



}
