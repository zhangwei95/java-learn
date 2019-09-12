package proxy;

public class UserMangerImpl implements UserManager {

    @Override
    public void addUser(String userId, String userName) {
        System.out.println(String.format("UserMangerImpl.addUser id=%s name=%s",userId,userName));
    }

    @Override
    public void delUser(String userId) {
        System.out.println(String.format("UserMangerImpl.delUser id=%s",userId));
    }

    @Override
    public String findUser(String userId) {
        return String.format("UserMangerImpl.findUser id=%s",userId);
    }

    @Override
    public void modifyUser(String userId, String userName) {
        System.out.println(String.format("UserMangerImpl.modifyUser id=%s name=%s",userId,userName));
    }
}
