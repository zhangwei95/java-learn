package behavior.state;

public class StatePatternTest {
    public static void main(String[] args) {
        Context context=new Context();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }
}
