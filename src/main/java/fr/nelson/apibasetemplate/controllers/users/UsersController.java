package fr.nelson.apibasetemplate.controllers.users;

import fr.nelson.apibasetemplate.exceptions.HttpException;
import fr.nelson.apibasetemplate.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("register")
    public String register(@RequestBody() UsersForm usersForm) {
        try{
            System.out.println(usersForm.getPassword());
            return this.usersService.register(usersForm);
        }catch (HttpException e) {
            return e.getMessage();
        }
    }
}
