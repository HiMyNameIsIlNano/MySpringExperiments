package X11_Bean_Scopes.impl;

import org.springframework.stereotype.Component;

@Component
public class LoginAction {

    private String username;
    private String password;

    public LoginAction() {
    }

    public LoginAction(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
