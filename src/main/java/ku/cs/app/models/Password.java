package ku.cs.app.models;

public class Password {
    private String password;
    private String errorMsg;

    public Password() {}

    private boolean checkUpperCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkLowerCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void setPassword(String password) {
        errorMsg = "";
        if (password.length() < 8) {
            errorMsg += "Password must be at least 8 characters.\n";
        }
        if (!checkUpperCase(password)) {
            errorMsg += "Password must contain uppercase.\n";
        }
        if (!checkLowerCase(password)) {
            errorMsg += "Password must contain lowercase.\n";
        }
        if (!checkDigit(password)) {
            errorMsg += "Password must contain digit.\n";
        }
        if (errorMsg == "") {
            this.password = password;
        }
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
