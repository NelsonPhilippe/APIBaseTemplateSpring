package fr.nelson.apibasetemplate.repositories.users;

import fr.nelson.apibasetemplate.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {
}
