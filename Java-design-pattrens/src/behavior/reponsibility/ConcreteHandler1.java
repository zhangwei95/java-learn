package behavior.reponsibility;

/**
 * 具体处理者角色1
 * 实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者
 */
public class ConcreteHandler1 extends Handler {

    @Override
    public void handleRequest(String request) {
        if ("one".equals(request)) {
            System.out.println("具体处理者1负责处理该请求！");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}
