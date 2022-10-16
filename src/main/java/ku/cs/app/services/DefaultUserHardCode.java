package ku.cs.app.services;

import ku.cs.app.models.User;
import ku.cs.app.models.list.UserList;

public class DefaultUserHardCode {
    private UserList defaultUser;
    public DefaultUserHardCode() {
        defaultUser = new UserList();
        setDefaultUser();
    }
    private void setDefaultUser() {
        User user = new User();
        user.setUsername("Tester303");
        user.setPassword("Tester123456");
    }
}
