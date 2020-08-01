package algorithm.graph;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.*;

/**
 * @Description: 描述
 * 一个用户 有3个属性 a b c  任意两个用户 只要有一个属性相同 那么 两个用户是同一个人
 * 合并完之后有几个用户
 * @Author: zhangwei
 * @Date: 2020/7/14 23:39
 */
public class T001_MergeUser {

    public static class User{
        public String a;
        public String b;
        public String c;

        public User(String a,String b,String c){
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return "User{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    ", c='" + c + '\'' +
                    '}';
        }
    }


    public static int mergeUser(List<User> users){
        T00_UnionFind.UnionSet<User> userUnionSet = new T00_UnionFind.UnionSet<>(users);
        Map<String,User> aMap = new HashMap<>();
        Map<String,User> bMap = new HashMap<>();
        Map<String,User> cMap = new HashMap<>();
        users.forEach(item->{
            if (aMap.containsKey(item.a)){
                userUnionSet.union(item,aMap.get(item.a));
            } else {
                aMap.put(item.a,item);
            }
            if (bMap.containsKey(item.b)){
                userUnionSet.union(item,bMap.get(item.b));
            } else {
                bMap.put(item.b,item);
            }
            if (cMap.containsKey(item.c)){
                userUnionSet.union(item,cMap.get(item.c));
            } else {
                cMap.put(item.c,item);
            }
        });

        return userUnionSet.getSize();

    }





    public static List<User> generateUser(int userSize){
        List<User> result  = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= userSize ; i++){
            User user = new User("a" + rand.nextInt(userSize+1),"b"+ rand.nextInt(userSize+1),"c"+ rand.nextInt(userSize+1));
            result.add(user);
        }
        return result;
    }



    public static void main(String[] args) {
        List<User> users = generateUser(5);
        System.out.println(Arrays.toString(users.toArray()));
        int size = mergeUser(users);
        System.out.println(size);
    }


}
