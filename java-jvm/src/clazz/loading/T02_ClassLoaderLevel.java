package clazz.loading;

public class T02_ClassLoaderLevel {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(T02_ClassLoaderLevel.class.getClassLoader());

        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(T02_ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());

//        System.out.println(new T02_ClassLoaderLevel().getParent());
//        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
