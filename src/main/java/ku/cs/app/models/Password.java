package ku.cs.app.models;

public class Password {
    //-------------------------------------------- instance

    private String password;
    private String errorMessage;

    //-------------------------------------------- constructor

    public Password() {
        this.password = "";
        this.errorMessage = "";
    }

    public Password(String password) {
        this.password = password;
    }

    //-------------------------------------------- method

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
        errorMessage = "";
        if (password.length() < 8) {
            errorMessage += "Password must be at least 8 characters.\n";
        }
        if (!checkUpperCase(password)) {
            errorMessage += "Password must contain uppercase.\n";
        }
        if (!checkLowerCase(password)) {
            errorMessage += "Password must contain lowercase.\n";
        }
        if (!checkDigit(password)) {
            errorMessage += "Password must contain digit.\n";
        }
        if (errorMessage == "") {
            this.password = password;
        }else {
            System.err.println(errorMessage);
        }
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private boolean isPasswordEqual(String p1, String p2){
        if (p1.equals(p2)) {return true;}
        return false;
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
