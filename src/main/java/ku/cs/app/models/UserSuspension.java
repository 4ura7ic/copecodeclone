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
    public UserSuspension(String username) {
        this.username = username;
        this.reason = "";
        this.loginAttempt = 0;
    }

    //-------------------------------------------- getter

    public String getUsername() {
        return username;
    }

    public String getReason() {
        return reason;
    }
    public int getLoginAttempt() { return loginAttempt; }

    public void setReason(String reason) {
        this.reason = reason;
    }
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
