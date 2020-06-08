package clazz.loading;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;


/**
 * 自定义ClassLoader
 * @author zhangwei
 */
public class T04_MyClassLoader extends ClassLoader {

    /**
     * 重写findClass
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File("./", name.replace(".", "/").concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;

            while ((b=fis.read()) !=0) {
                baos.write(b);
            }

            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();//可以写的更加严谨

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name); //throws ClassNotFoundException
    }
    public static void main(String[] args) throws Exception {
        ClassLoader l = new T04_MyClassLoader();
        Class clazz = l.loadClass("clazz.loading.BaseTest");

        Class clazz1 = l.loadClass("clazz.loading.TestImpl");


        System.out.println(clazz == clazz1);
        BaseTest t = (BaseTest) clazz1.newInstance();
        t.method1();

        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());

        System.out.println(getSystemClassLoader());
    }
}
