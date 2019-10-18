package behavior.command;

public class CommandPatternTest {
    public static void main(String[] args) {
        Invoke invoke=new Invoke(new ConcreteCommand());
        invoke.call();
    }
}
