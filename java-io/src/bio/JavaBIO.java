package bio;

import java.io.*;
import java.util.Arrays;

public class JavaBIO {


    public static void main(String[] args) throws Exception {
        //读文件 字节流
//        File file = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/a.txt");
//        InputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[(int) file.length()];
//        int read = inputStream.read(bytes);
//        System.out.println(new String(bytes));
//        inputStream.close();

        //写文件  字节流
//        String var = "hai this is a test";
//        File ouFile = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/b.txt");
//        OutputStream outputStream = new FileOutputStream(ouFile);
//        outputStream.write(var.getBytes());
//        outputStream.close();


        //读文件 字符流
//        File file = new File("D:\\\\gitRepository\\\\java-learn\\\\java-io\\\\src\\\\bio/a.txt");
//        Reader reader = new FileReader(file);
//        char[] bytes = new char[(int) file.length()];
//        reader.read(bytes);
//        System.out.println(new String(bytes));
//        reader.close();

        //写文件 字符流
//        String var = "hai this is a test";
//        File ouFile = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/b.txt");
//        Writer writer = new FileWriter(ouFile);
//        writer.write(var);
//        writer.close();


        //读文件 缓冲区
//        File file = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/a.txt");
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file),16);
//        int read = 0;
//        byte[] bytes = new byte[16];
//        while ((read = bufferedInputStream.read(bytes))!=-1){
//            System.out.println(new String(bytes,0,read));
//            System.out.println("read");
//        }
//        bufferedInputStream.close();

        //写文件 缓冲区
//        String var = "hai this is a test";
//        File ouFile = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/b.txt");
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(ouFile),16);
//        bufferedOutputStream.write(var.getBytes(),0,var.getBytes().length);
//        bufferedOutputStream.flush();
//        bufferedOutputStream.close();

        //读 a 写 b
//        File file = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/a.txt");
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file),16);
//
//        File ouFile = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/b.txt");
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(ouFile),16);
//
//        int read = 0;
//        byte[] bytes = new byte[16];
//        while ((read = bufferedInputStream.read(bytes))!=-1){
//            bufferedOutputStream.write(bytes,0,read);
//        }
//        bufferedOutputStream.flush();
//        bufferedInputStream.close();
//        bufferedOutputStream.close();

        //读 a 写 b
        File file = new File("D:\\\\gitRepository\\\\java-learn\\\\java-io\\\\src\\\\bio/a.txt");
        Reader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader, 16);

        File ouFile = new File("D:\\gitRepository\\java-learn\\java-io\\src\\bio/b.txt");
        Writer writer = new FileWriter(ouFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer, 16);
        int flag = 0;
        char[] bytes = new char[16];
        while ((flag = bufferedReader.read(bytes)) != -1) {
            bufferedWriter.write(new String(bytes, 0, flag));
        }
        bufferedWriter.flush();
        reader.close();
        writer.close();
    }
}
