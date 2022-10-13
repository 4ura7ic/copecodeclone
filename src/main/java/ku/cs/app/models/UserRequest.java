package ku.cs.app.models;

public class UserRequest {
    private String username;
    private String confession;

    public UserRequest(String username, String confession) {
        this.username = username;
        this.confession = confession;
    }

    public String getUsername() {
        return username;
    }

    public String getConfession() {
        return confession;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setConfession(String confession) {
        this.confession = confession;
    }
}
