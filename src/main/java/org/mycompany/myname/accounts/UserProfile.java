package org.mycompany.myname.accounts;

public class UserProfile {
    private final String login;
    private final String pass;
    private final String email;

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }
}
