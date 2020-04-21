public class JvmUseCaseTest {

    public int compute(){
        int i=1;
        int j=2;
        int k=i+j*10;
        return k;
    }
    public static  JvmUseCaseTest jvmUseCaseTest=new JvmUseCaseTest();
    /**
     * javap -c JvmUseCaseTest.class
     * java class jvm
     * -XX:TraceClassLoading
     * */
    public static void main(String[] args) {
        int i = -5;
        i >>>= 1;
        int j = 3;

        int k = -2;
        k = k>>1;
        JvmUseCaseTest jvmUseCaseTest=new JvmUseCaseTest();
//        int compute = jvmUseCaseTest.compute();
//        System.out.println(compute);
    }
}
