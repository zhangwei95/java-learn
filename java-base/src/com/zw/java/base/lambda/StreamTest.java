package com.zw.java.base.lambda;

import com.zw.java.base.localdate.timeutil.strategy.week.Strategy;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * @Description: stream 用法
 * @Author: zhangwei
 * https://www.cnblogs.com/gaopengfirst/p/10813803.html 参考stream用法
 * @Date: 2020/8/1 21:08
 */
public class StreamTest {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L,"zs",18));
        users.add(new User(2L,"ls",20));
        users.add(new User(3L,"ww",21));
        users.add(new User(4L,"zl",22));
        users.add(new User(5L,"sq",24));
        users.add(new User(3L,"ww",21));
    }



    @Test
    public void map(){
        List<Long> collect = users.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    @Test
    public void flatMap(){
        List<String> collect = users.stream().map(item -> item.getName().split(""))
                .flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void filter(){
        List<User> collect = users.stream().filter(item -> item.age > 20).collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    @Test
    public void distinct(){
        List<User> collect = users.stream().distinct().collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    @Test
    public void allMatch(){
        boolean b = users.stream().allMatch(item -> item.getAge() > 19);
        System.out.println(b);
    }

    @Test
    public void anyMatch(){
        boolean b = users.stream().anyMatch(item -> item.getAge() > 20);
        System.out.println(b);
    }

    @Test
    public void noneMatch(){
        boolean b = users.stream().noneMatch(item -> item.age > 30);
        System.out.println(b);
    }

    @Test
    public void findFirst(){
        Optional<User> first = users.stream().filter(item -> item.getAge() > 20).findFirst();
        System.out.println(first.orElse(new User(1L,"default",0)).toString());
    }

    @Test
    public void reduce(){
        Optional<Integer> reduce = users.stream().map(User::getAge).reduce((a, c) -> a + c);
        Optional<Integer> reduce1 = users.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(reduce.get());
        System.out.println(reduce1.get());
    }

    @Test
    public void counting(){
        long count = users.stream().count();
        System.out.println(count);
    }

    @Test
    public void maxBy(){
        User user = users.stream().collect(Collectors.maxBy((u1, u2) -> u1.getAge() - u2.getAge())).get();
        User user1 = users.stream().max((u1, u2) -> u1.getAge() - u2.getAge()).get();
        User user2 = users.stream().max(Comparator.comparingInt(User::getAge)).get();
        System.out.println(user1.toString());
    }

    @Test
    public void average(){
        Double collect = users.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println(collect);
    }

    /**
     * 统计
     */
    @Test
    public void summarizing(){
        IntSummaryStatistics collect = users.stream().collect(Collectors.summarizingInt(User::getAge));
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
        System.out.println(collect.getAverage());
        System.out.println(collect.getSum());
        System.out.println(collect.getCount());
    }

    /**
     * 字符串拼接
    * */
    @Test
    public void join(){
        String collect = users.stream().map(item -> item.getId() + ":" + item.getName()).collect(Collectors.joining(","));
        System.out.println(collect);
    }


    @Test
    public void groupBy(){
        System.out.println("分组");
        Map<Long, List<User>> collect1 = users.stream().collect(Collectors.groupingBy(User::getId));
        Map<Long, Map<Integer, Long>> collect2 = users.stream().collect(Collectors.groupingBy(User::getId, Collectors.groupingBy(User::getAge, Collectors.counting())));
        Map<Long, Map<Integer, Map<String, Long>>> collect4 = users.stream().collect(Collectors.groupingBy(User::getId, Collectors.groupingBy(User::getAge, Collectors.groupingBy(User::getName,Collectors.counting()))));
        System.out.println("collect1 = " + collect1);
        System.out.println("collect2 = " + collect2);
        System.out.println("collect4 = " + collect4);
    }

    /**
     * 分区，分区可以看做是分组的一种特殊情况，在分区中key只有两种情况：true或false，目的是将待分区集合按照条件一分为二，
     * java8的流式处理利用ollectors.partitioningBy()方法实现分区
     */
    @Test
    public void partitioningBy(){
        System.out.println("分区");
        Map<Boolean, List<User>> collect5 = users.stream().collect(Collectors.partitioningBy(item -> item.getAge()<=20));
        System.out.println("collect5 = " + collect5);
    }
    @Test
    public void stream(){
        List<User> list= new ArrayList<>();
        Map<String,Object> map= new HashMap<>();
        Set<String> stream = new HashSet<>();
        Set<Long> collect = list.stream().map(User::getId).collect(Collectors.toSet());


    }

    static class User{
        private Long id;

        private String name;

        private int age;

        User(Long id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return id.equals(user.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
