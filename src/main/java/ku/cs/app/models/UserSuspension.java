package ku.cs.app.models;

public class UserSuspension {
    private String username;
    private String reason;
    private int loginAttempt;


    public UserSuspension(String username, String reason, int loginAttempt) {
        this.username = username;
        this.reason = reason;
        this.loginAttempt = loginAttempt;
    }

    public String getUsername() {
        return username;
    }

    public String getReason() {
        return reason;
    }
    public int getLoginAttempt() { return loginAttempt; }

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
