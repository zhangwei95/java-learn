package clazz.loading;

public class T05_LoadClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = T05_LoadClass.class.getClassLoader().loadClass("clazz.loading.TestImpl");
        System.out.println(clazz.getName());
    }
}
