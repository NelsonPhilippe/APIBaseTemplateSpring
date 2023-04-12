package fr.nelson.apibasetemplate.services.users;

import fr.nelson.apibasetemplate.controllers.users.UsersForm;
import fr.nelson.apibasetemplate.repositories.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void register(UsersForm usersForm) {

    }

}
