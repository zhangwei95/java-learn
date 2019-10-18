package behavior.state;

/**
 * 环境类
 */
public class Context {
    private State state;

    public Context(){
        //初始化环境状态
        this.state = new ConcreteStateA();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void handle() {
        state.handle(this);
    }
}

