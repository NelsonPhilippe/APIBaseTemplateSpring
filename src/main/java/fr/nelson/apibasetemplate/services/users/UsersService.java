package fr.nelson.apibasetemplate.services.users;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import fr.nelson.apibasetemplate.controllers.users.forms.UsersForm;
import fr.nelson.apibasetemplate.entities.UsersEntity;
import fr.nelson.apibasetemplate.exceptions.HttpException;
import fr.nelson.apibasetemplate.repositories.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public String register(UsersForm usersForm) throws HttpException {
        Optional<UsersEntity> usersEntity = usersRepository.findByEmail(usersForm.getEmail());
        System.out.println("register user");
        if(usersEntity.isPresent()){
            System.out.println("user already exist");
            throw new HttpException("Account already exist", 509);
        }

        try{
            usersRepository.insert(new UsersEntity(usersForm.getEmail(), usersForm.getPassword()));
        }catch (Exception e){
            System.out.println("fail saved");
            throw new HttpException("Saving in database failed", 400);
        }
        return "User successfully registered";
    }

    public String updateEmail(String id, String newMail) throws HttpException {
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);

        if(usersEntity.isPresent()){
            try{
                usersEntity.get().setEmail(newMail);
                usersRepository.save(usersEntity.get());
                return "Email successfully updated";
            }catch (Exception e) {
                throw new HttpException("Saving in database failed", 400);
            }
        }
        throw new HttpException("User not found", 404);
    }

    public String updatePassword(String id, String oldPassword, String newPassword) throws HttpException {
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);

        if(usersEntity.isPresent()){

            if(!Objects.equals(oldPassword, usersEntity.get().getPassword()))
                throw new HttpException("Old password is not correct", 400);

            try{
                usersEntity.get().setPassword(newPassword);
                usersRepository.save(usersEntity.get());
                return "Password successfully updated";
            }catch (Exception e) {
                throw new HttpException("Saving in database failed", 400);
            }
        }
        throw new HttpException("User not found", 404);
    }

    public String deleteUser(String id) throws HttpException {
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);

        if(usersEntity.isPresent()){
            try{
                usersRepository.delete(usersEntity.get());
                return "User successfully deleted";
            }catch (Exception e) {
                throw new HttpException("Saving in database failed", 400);
            }
        }
        throw new HttpException("User not found", 404);
    }

    public JsonElement getAllUsers() throws HttpException {
        try{
            List<UsersEntity> entities = usersRepository.findAll();
            Gson gson = new Gson();
            return gson.toJsonTree(entities);
        }catch (Exception e) {
            throw new HttpException("Get in database failed", 400);
        }
    }

    public JsonElement getUserById(String id) throws HttpException {
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);

        if(usersEntity.isPresent()){
            try{
                Gson gson = new Gson();
                return gson.toJsonTree(usersEntity.get());
            }catch (Exception e) {
                throw new HttpException("Get in database failed", 400);
            }
        }
        throw new HttpException("User not found", 404);
    }

    public JsonElement getUserByMail(String mail) throws HttpException {
        Optional<UsersEntity> usersEntity = usersRepository.findByEmail(mail);

        if(usersEntity.isPresent()){
            try{
                Gson gson = new Gson();
                return gson.toJsonTree(usersEntity.get());
            }catch (Exception e) {
                throw new HttpException("Get in database failed", 400);
            }
        }
        throw new HttpException("User not found", 404);
    }
}
