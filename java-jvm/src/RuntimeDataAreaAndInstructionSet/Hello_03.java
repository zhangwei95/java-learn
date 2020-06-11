package RuntimeDataAreaAndInstructionSet;

public class Hello_03 {
    public static void main(String[] args) {
        // 0 new #2 <RuntimeDataAreaAndInstructionSet/Hello_03>
        // 3 dup  Duplicate the top operand stack value
        // 4 invokespecial #3 <RuntimeDataAreaAndInstructionSet/Hello_03.<init>>  构造函数
        // 7 astore_1  保存到局部变量表
        // 8 aload_1  读取到操作数栈
        // 9 invokevirtual #4 <RuntimeDataAreaAndInstructionSet/Hello_03.m1>  调用m1方法
        // 12 istore_2 保存到局部变量表
        // 13 return
        Hello_03 h = new Hello_03();
        int i = h.m1();
    }

    public int m1() {
        // 0 bipush 100 压栈
        // 2 ireturn  Return int from method  出当前方法栈 并进入调用者的方法栈帧中
        return 100;
    }
}
