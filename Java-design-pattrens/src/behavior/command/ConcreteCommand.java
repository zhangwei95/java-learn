package behavior.command;

/**
 * 具体命令
 * 抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作
 */
public class ConcreteCommand implements Command {

    private Receive receive;

    ConcreteCommand(){
        receive=new Receive();
    }

    @Override
    public void execute() {
        System.out.println("具体命令 执行！");
        receive.action();
    }
}
