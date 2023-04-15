package fr.nelson.apibasetemplate.entities;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class UsersEntity {

    @Id
    private String id;

    private String uuid;

    private String email;
    private String password;

    private String role;

    public UsersEntity(String email, String password) {
        super();
        this.email = email;
        this.password = password;
        this.uuid = UUID.randomUUID().toString();
        this.role = null;
    }

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
