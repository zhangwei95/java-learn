package clazz.loading;

public class T06_ClassReloading {

    public static void main(String[] args) throws ClassNotFoundException {

        T04_MyClassLoader classLoader = new T04_MyClassLoader();
        Class<?> aClass = classLoader.loadClass("clazz.loading.TestImpl");

        classLoader = null;
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.hashCode());

        classLoader = null;

        classLoader = new T04_MyClassLoader();

        Class<?> bClass =  classLoader.loadClass("clazz.loading.TestImpl");
        System.out.println(bClass.getClassLoader());
        System.out.println(bClass.hashCode());

        System.out.println(aClass == bClass);
    }
}
