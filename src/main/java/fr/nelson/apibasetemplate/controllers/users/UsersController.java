package fr.nelson.apibasetemplate.controllers.users;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {
    @PostMapping("register")
    public void register(@RequestBody() UsersForm usersForm) {

    }
}
