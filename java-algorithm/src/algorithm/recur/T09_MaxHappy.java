package algorithm.recur;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 员工最大高兴值
 * 公司聚会
 * 员工参加会获得高兴值
 * 但是 上级领导参加了 直接下属就不会参加
 * 怎么参加让 整体高兴值最高
 * @Author: zhangwei
 * @Date: 2020/7/10 10:18
 */
public class T09_MaxHappy {


    static class Employee{
        int happy;
        List<Employee> nexts;
        public Employee(int happy){
            this.happy = happy;
            this.nexts = new ArrayList<>();
        }
    }


    public static int maxHappy(Employee boss){
        if (boss == null){
            return 0;
        }
        return process(boss, false);
    }

    public static int process(Employee leader,boolean leaderPresent){
        int max = 0;
        if (leaderPresent){
            for(Employee next:leader.nexts){
                max += process(next,false);
            }
            return max;
        } else {
            int e1 = leader.happy;
            int e2 = 0;
            for(Employee next:leader.nexts){
                e1 += process(next,true);
                e2 += process(next,false);
            }
            return Math.max(e1, e2);
        }

    }

    static class Info{
        int yes;
        int no;
        Info(int yes , int no){
            this.yes = yes;
            this.no = no;
        }
    }

    public static int maxHappy1(Employee employee){
        if (employee == null){
            return 0;
        }
        Info process = process(employee);
        return Math.max(process.yes, process.no);
    }

    public static Info process(Employee employee){
        if (employee == null){
             return new Info(0,0);
        }
        int yes = employee.happy;
        int no = 0;
        for(Employee e:employee.nexts){
            Info process = process(e);
            yes += process.no;
            no += Math.max(process.yes,process.no);
        }
        return new Info(yes,no);
    }



    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy(boss) != maxHappy1(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }




















}
