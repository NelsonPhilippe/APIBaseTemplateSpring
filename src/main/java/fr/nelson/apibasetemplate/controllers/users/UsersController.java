package fr.nelson.apibasetemplate.controllers.users;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import fr.nelson.apibasetemplate.controllers.users.forms.EmailForm;
import fr.nelson.apibasetemplate.controllers.users.forms.PasswordForm;
import fr.nelson.apibasetemplate.controllers.users.forms.UsersForm;
import fr.nelson.apibasetemplate.exceptions.HttpException;
import fr.nelson.apibasetemplate.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users", consumes= "application/json")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody() UsersForm usersForm) {
        try{
            String message = this.usersService.register(usersForm);
            return new ResponseEntity<>(message, null, 200);
        }catch (HttpException e) {
            return new ResponseEntity<>(e.getMessage(), null, e.getStatusCode());
        }
    }

    @PatchMapping("update/email/{id}")
    public ResponseEntity<String> updateEmail(@PathVariable String id, @RequestBody() EmailForm newMail) {
        try{
            String message = this.usersService.updateEmail(id, newMail.getEmail());
            return new ResponseEntity<>(message, null, 200);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getMessage(), null, e.getStatusCode());
        }
    }

    @PatchMapping("update/password/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable String id, @RequestBody() PasswordForm newPassword) {
        try{
            String message = this.usersService.updatePassword(id, newPassword.getOldPassword(), newPassword.getPassword());
            return new ResponseEntity<>(message, null, 200);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getMessage(), null, e.getStatusCode());
        }
    }

    @GetMapping("find/mail/{email}")
    public ResponseEntity<JsonElement> findUserByEmail(@PathVariable String email) {
        try{
            JsonElement message = this.usersService.getUserByMail(email);
            return new ResponseEntity<>(message, null, 200);
        } catch (HttpException e) {
            Gson gson = new Gson();
            return new ResponseEntity<>(gson.toJsonTree(e.getMessage()), null, e.getStatusCode());
        }
    }

    @GetMapping("find/id/{id}")
    public ResponseEntity<JsonElement> findUserById(@PathVariable String id) {
        try{
            JsonElement message = this.usersService.getUserById(id);
            return new ResponseEntity<>(message, null, 200);
        } catch (HttpException e) {
            Gson gson = new Gson();
            return new ResponseEntity<>(gson.toJsonTree(e.getMessage()), null, e.getStatusCode());        }
    }

    @GetMapping("find/all")
    public ResponseEntity<JsonElement> findAllUsers() {
        try{
            JsonElement message = this.usersService.getAllUsers();
            return new ResponseEntity<>(message, null, 200);
        } catch (HttpException e) {
            Gson gson = new Gson();
            return new ResponseEntity<>(gson.toJsonTree(e.getMessage()), null, e.getStatusCode());        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try{
            String message = this.usersService.deleteUser(id);
            return new ResponseEntity<>(message, null, 200);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getMessage(), null, e.getStatusCode());
        }
    }
}
