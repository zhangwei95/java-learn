public class SubObject extends Object {

    public static SubObject instance=null;

    public void isAlive(){
        System.out.println("isAlive");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize methode executed");
        instance = this;
    }

    public static void main(String[] args) throws InterruptedException {
        instance= new SubObject();
        instance=null;
        System.gc();
        Thread.sleep(1000);
        //假如未重写finalize方法，这个时候对象已经被回收了
        instance.isAlive();

        instance=null;
        //第二次GC，不会再执行finalize方法
        System.gc();
        Thread.sleep(1000);
        instance.isAlive();
    }
}
