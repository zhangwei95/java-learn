package jmm;


/**
 * 合并写
 * 和CPU核心数有关
 */
public class A05_WriteCombining {

    private static final int ITERATIONS = Integer.MAX_VALUE;
    private static final int ITEMS = 1 << 24;
    private static final int MASK = ITEMS - 1;

    private static final byte[] arrayA = new byte[ITEMS];
    private static final byte[] arrayB = new byte[ITEMS];
    private static final byte[] arrayC = new byte[ITEMS];
    private static final byte[] arrayD = new byte[ITEMS];
    private static final byte[] arrayE = new byte[ITEMS];
    private static final byte[] arrayF = new byte[ITEMS];
    private static final byte[] arrayG = new byte[ITEMS];
    private static final byte[] arrayH = new byte[ITEMS];
    private static final byte[] arrayI = new byte[ITEMS];
    private static final byte[] arrayJ = new byte[ITEMS];
    private static final byte[] arrayK = new byte[ITEMS];
    private static final byte[] arrayL = new byte[ITEMS];


    public static void main(final String[] args) {
        for (int i = 1; i <= 3; i++) {
            System.out.println(i + " SingleLoop duration (ns) = " + runCaseOne());
            System.out.println(i + " SplitLoop  duration (ns) = " + runCaseTwo());
        }
    }

    public static long runCaseOne() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
            arrayG[slot] = b;
            arrayH[slot] = b;
            arrayI[slot] = b;
            arrayJ[slot] = b;
            arrayK[slot] = b;
            arrayL[slot] = b;

        }
        return System.nanoTime() - start;
    }

    public static long runCaseTwo() {
        long start = System.nanoTime();
        int i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
        }
        i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayG[slot] = b;
            arrayH[slot] = b;
            arrayI[slot] = b;
            arrayJ[slot] = b;
            arrayK[slot] = b;
            arrayL[slot] = b;
        }
        return System.nanoTime() - start;
    }
}
