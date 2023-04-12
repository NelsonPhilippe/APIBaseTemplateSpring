package fr.nelson.apibasetemplate.entities;

import org.springframework.data.annotation.Id;

public class UsersEntity {

    @Id
    private String id;

    private String email;
    private String password;

    public UsersEntity(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
