package RuntimeDataAreaAndInstructionSet;

public class Hello_01 {
    public static void main(String[] args) {
        int i = 127;
        // 0 bipush 100 -128~127 数（8bit带符号） 压栈
        // 2 istore_1  保存局部变量表 1位置
        // 3 return 方法返回 弹出栈帧 Return void from method
    }

    public void m1() {
        int i = 128;

        // 0 sipush -32768~32767数（16bit带符号） 压栈
        // 3 istore_1  保存局部变量表 1位置
        // 4 return
    }

    public void m2(int k) {
        // 0 ldc #2 <32769>  Push item from run-time constant pool 把常量池中的项压入栈
        // 2 istore_2 保存到局部变量表的2号位置
        // 3 ldc #3 <32768>  把常量池中的项压入栈
        // 5 istore_3 保存到局部变量表的3号位置
        // 6 sipush 32767  short 数入栈
        // 9 istore 4  int 数 入栈
        // 11 sipush 32766
        // 14 istore 5  short 数入栈
        // 16 return  int 数 入栈
        int p = 32769;
        int i = 32768;
        int j = 32767;
        int o = 32766;
    }

    public void add(int a, int b) {
        // 0 iload_1  读取局部变量表1号位置数 到 操作数栈
        // 1 iload_2  读取局部变量表2号位置数 到 操作数栈
        // 2 iadd  从操作数栈中弹出2个数相加 结果 压栈
        // 3 istore_3 出栈 保存到局部变量表3号位置
        // 4 return
        int c = a + b;
    }

    public void m3() {
        // 0 aconst_null   Push the null object reference onto the operand stack
        // 1 astore_1  Store reference into local variable
        // 2 return
        Object o = null;
    }

    public void m4() {
        // 0 new #2 <java/lang/Object>    Create new object
        // 3 dup  Duplicate the top operand stack value
        // 4 invokespecial #1 <java/lang/Object.<init>>  Invoke instance method
        // 7 astore_1   Store reference into local variable
        // 8 return
        Object o = new Object();
    }


}
