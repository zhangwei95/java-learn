package proxy;

import com.sun.media.jfxmedia.events.NewFrameEvent;

public class Person  {

    public static void main(String[] args) {
//        UserManager userManager=new UserManagerImplProxy(new UserMangerImpl());
//        userManager.addUser("1","张三");
        LogHandler logHandler=new LogHandler();
        UserManager userManager1 = (UserManager)logHandler.newProxyInstance(new UserMangerImpl());
        userManager1.addUser("1111","张三");
        assert true:"yyy";
    }


}
