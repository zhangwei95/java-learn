package map.bean;


import java.util.ArrayList;
import java.util.List;

public class test {
    static class User{
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "User{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
        }
    }

    public static void main(String[] args){
        Teacher teacher= new Teacher();

        List<User> users=new ArrayList<>();
        User a=new User();
        a.setA("a");
        a.setB("b");
        users.add(a);
        System.out.println(users);
        a.setA("b");
        System.out.println(users);
    }
}
