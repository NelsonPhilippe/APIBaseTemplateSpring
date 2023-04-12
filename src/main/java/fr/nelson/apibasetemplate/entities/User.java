package fr.nelson.apibasetemplate.entities;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;

    private String email;
    private String password;

    public User(String id, String email, String password) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
