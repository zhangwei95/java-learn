package interview_A1B2C3;


public class T04_CAS {

    static volatile boolean t1Run = false;

    public static void main(String[] args) {

        new Thread(()->{
            for(Integer i : Constant.number){
                while (!t1Run){}
                System.out.println(i);
                t1Run = false;

            }
        },"t1").start();

        new Thread(()->{
            for(char i : Constant.word){
                while (t1Run){}
                System.out.println(i);
                t1Run = true;
            }
        },"t2").start();
    }
}
