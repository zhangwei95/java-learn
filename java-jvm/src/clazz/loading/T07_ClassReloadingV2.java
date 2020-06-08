package clazz.loading;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class T07_ClassReloadingV2 {

    private static class MyLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {

            File f = new File("D:/gitRepository/java-learn/out/production/java-jvm/" + name.replace(".", "/").concat(".class"));

            if(!f.exists()) return super.loadClass(name);

            try {

                InputStream is = new FileInputStream(f);

                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return super.loadClass(name);
        }
    }

    public static void main(String[] args) throws Exception {
        MyLoader m = new MyLoader();
        Class clazz = m.loadClass("clazz.loading.Hello");
        System.out.println(m.hashCode());
        System.out.println(clazz.getClassLoader());
        m = new MyLoader();
        Class clazzNew = m.loadClass("clazz.loading.Hello");
        System.out.println(clazzNew.getClassLoader());
        System.out.println(clazz == clazzNew);
    }
}
