package RuntimeDataAreaAndInstructionSet;

public class Hello_02 {
    public static void main(String[] args) {
        // 0 new #2 <RuntimeDataAreaAndInstructionSet/Hello_02>
        // 3 dup   Duplicate the top operand stack value
        // 4 invokespecial #3 <RuntimeDataAreaAndInstructionSet/Hello_02.<init>> Invoke instance method
        // 7 astore_1 将h保存到局部变量表
        // 8 aload_1 读取局部变量表1号位置到操作数栈
        // 9 invokevirtual #4 <RuntimeDataAreaAndInstructionSet/Hello_02.m1> Invoke instance method; dispatch based on class
        // 12 return
        Hello_02 h = new Hello_02();
        h.m1();
    }

    public void m1() {
        int i = 200;
    }

    public void m2(int k) {
        int i = 300;
    }

    public void add(int a, int b) {
        int c = a + b;
    }

    public void m3() {
        Object o = null;
    }

    public void m4() {
        Object o = new Object();
    }


}
