package fr.nelson.apibasetemplate.services.users;

import fr.nelson.apibasetemplate.controllers.users.UsersForm;
import fr.nelson.apibasetemplate.entities.UsersEntity;
import fr.nelson.apibasetemplate.exceptions.HttpException;
import fr.nelson.apibasetemplate.repositories.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
