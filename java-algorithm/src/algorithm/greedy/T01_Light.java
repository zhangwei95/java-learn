package algorithm.greedy;

import java.util.HashSet;

/**
 * @Description: 灯光问题 贪心
 * 给定一个字符串X..XX....XX.X...X  代表一个街道
 * X代表墙  .代表房子
 * X位置不需要也不能放置灯
 * .位置可以放置灯，需要被照亮
 * 每个灯 可以照亮其 前 当前 以及后1位置
 * 如何 最少使用多少灯 可以照亮整个街道
 * @Author: zhangwei
 * @Date: 2020/7/10 13:59
 */
public class T01_Light {


    public static int minLight(String road) {
        if (road == null || road.length() == 0){
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    public static int process(char[] road, int index, HashSet<Integer> lights) {
        if (index == road.length) {
            for (int i = 0; i < road.length; i++) {
                if (road[i] == '.' && !lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                    return Integer.MAX_VALUE;
                }
            }
            return lights.size();
        } else {
            int no = process(road, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (road[index] == '.') {
                lights.add(index);
                yes = process(road, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public static int getMinLight(String road) {
        char[] chars = road.toCharArray();
        int index = 0;
        int light = 0;
        while (index < chars.length){
            if (chars[index] != '.'){
                index++;
            } else {
                light++;
                if (index + 1 == chars.length){
                    break;
                } else {
                    if (chars[index +1] == '.'){
                        index = index + 3;
                    } else {
                        index = index + 2;
                    }
                }

            }
        }
        return light;
    }


    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight(test);
            int ans2 = getMinLight(test);
            if (ans1 != ans2) {
                System.out.println(ans1 +"   "+ans2);
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
