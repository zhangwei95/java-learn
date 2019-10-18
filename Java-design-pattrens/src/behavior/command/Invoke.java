package behavior.command;

/**
 * 调用者
 */
public class Invoke {
    private Command command;
    public Invoke(Command command){
        this.command=command;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call(){
        System.out.println("调用者 调用命令！");
        command.execute();
    }
}
