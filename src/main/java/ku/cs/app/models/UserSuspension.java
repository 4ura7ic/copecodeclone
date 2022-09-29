package ku.cs.app.models;

public class UserSuspension {
    //-------------------------------------------- instance

    private String username;
    private String reason;
    private int loginAttempt;

    //-------------------------------------------- constructor

    public UserSuspension(String username, String reason, int loginAttempt) {
        this.username = username;
        this.reason = reason;
        this.loginAttempt = loginAttempt;
    }

    //-------------------------------------------- getter

    public String getUsername() {
        return username;
    }

    public String getReason() {
        return reason;
    }
    public int getLoginAttempt() { return loginAttempt; }

    //-------------------------------------------- method

    public void addLoginAttempt() {
        this.loginAttempt += 1;
    }

    @Override
    public String toString() {
        return "UserSuspension{" +
                "username='" + username + '\'' +
                ", reason='" + reason + '\'' +
                ", loginAttempt=" + loginAttempt +
                '}';
    }
}
