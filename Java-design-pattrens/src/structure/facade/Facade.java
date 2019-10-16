package structure.facade;

/**
 * 外观
 */
public class Facade {
    private SubSystemOne obj1=new SubSystemOne();
    private SubSystemTwo obj2=new SubSystemTwo();
    private SubSystemThree obj3=new SubSystemThree();
    public void method() {
        obj1.method();
        obj2.method();
        obj3.method();
    }
}
