package create.singleton;

/**
 * 单例模式：懒汉模式
 */
public class LazySingleton {
    private static volatile LazySingleton instance=null;
    private LazySingleton(){}

    private static synchronized LazySingleton getInstance(){
        if(instance==null){
            instance=new LazySingleton();
        }
        return instance;
    }
}
