package nio.reactor;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/13 17:46
 */
public class Test {

    public static void main(String[] args) {
        SelectorThreadGroup boss = new SelectorThreadGroup(3);

        SelectorThreadGroup worker = new SelectorThreadGroup(3);

        boss.setWorker(worker);

        boss.bind(9090);
        boss.bind(9091);
        boss.bind(9092);
        boss.bind(9093);

    }
}
