package RuntimeDataAreaAndInstructionSet;

/**
 * 一个线程 一个虚拟机栈 JVM Stack 一个虚拟机包含多个栈帧
 * 栈帧  frame  一个方法 一个栈帧
 * ++操作  局部变量表操作 Local variable
 * 赋值  操作数栈 Operand stack
 */
public class PlusPlusTest {
    public static void main(String[] args) {
        int i = 8;
        i = i++;// 输出8
//        i = ++i; // 输出9
        System.out.println(i);
    }
}
