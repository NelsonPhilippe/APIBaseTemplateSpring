package fr.nelson.apibasetemplate.controllers.users;

import fr.nelson.apibasetemplate.controllers.users.forms.EmailForm;
import fr.nelson.apibasetemplate.controllers.users.forms.PasswordForm;
import fr.nelson.apibasetemplate.controllers.users.forms.UsersForm;
import fr.nelson.apibasetemplate.exceptions.HttpException;
import fr.nelson.apibasetemplate.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("update/email/{id}")
    public String updateEmail(@PathVariable String id, @RequestBody() EmailForm newMail) {
        try{
            return this.usersService.updateEmail(id, newMail.getEmail());
        } catch (HttpException e) {
            return e.getMessage();
        }
    }

    @PatchMapping("update/password/{id}")
    public String updatePassword(@PathVariable String id, @RequestBody() PasswordForm newPassword) {
        try{
            return this.usersService.updatePassword(id, newPassword.getPassword());
        } catch (HttpException e) {
            return e.getMessage();
        }
    }
}
