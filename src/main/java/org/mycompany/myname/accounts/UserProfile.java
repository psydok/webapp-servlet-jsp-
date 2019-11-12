package org.mycompany.myname.accounts;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;

@Entity
@Table(name = "authorization")
public class UserProfile {

    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "pass")
    private String pass;
    @Column(name = "email")
    private String email;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserProfile() {
    }

    public UserProfile(String login, String pass, String email) {
        login = new String(login.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        this.login = login;
        this.pass = pass;
        this.email = email;
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
