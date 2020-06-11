package RuntimeDataAreaAndInstructionSet;


public class Hello_04 {
    public static void main(String[] args) {
        // 0 new #2 <RuntimeDataAreaAndInstructionSet/Hello_04>
        // 3 dup
        // 4 invokespecial #3 <RuntimeDataAreaAndInstructionSet/Hello_04.<init>>
        // 7 astore_1
        // 8 aload_1
        // 9 iconst_3
        // 10 invokevirtual #4 <RuntimeDataAreaAndInstructionSet/Hello_04.m>
        // 13 istore_2
        // 14 return
        Hello_04 h = new Hello_04();
        int i = h.m(3);
    }

    public int m(int n) {
        // 0 iload_1
        // 1 iconst_1
        // 2 if_icmpne 7 (+5)
        // 5 iconst_1
        // 6 ireturn
        // 7 iload_1
        // 8 aload_0
        // 9 iload_1
        // 10 iconst_1
        // 11 isub
        // 12 invokevirtual #4 <RuntimeDataAreaAndInstructionSet/Hello_04.m>
        // 15 imul
        // 16 ireturn
        if(n == 1) return 1;
        return n * m(n-1);
    }
}
