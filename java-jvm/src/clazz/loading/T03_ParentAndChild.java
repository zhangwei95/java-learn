package clazz.loading;

public class T03_ParentAndChild {

    public static void main(String[] args) {
        System.out.println(T03_ParentAndChild.class.getClassLoader());
        System.out.println(T03_ParentAndChild.class.getClassLoader().getClass().getClassLoader());
        System.out.println(T03_ParentAndChild.class.getClassLoader().getParent());
        System.out.println(T03_ParentAndChild.class.getClassLoader().getParent().getParent());
        //System.out.println(T03_ParentAndChild.class.getClassLoader().getParent().getParent().getParent());
    }
}
