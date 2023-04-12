package fr.nelson.apibasetemplate.repositories.users;

import fr.nelson.apibasetemplate.entities.UsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<UsersEntity, String> {

    Optional<UsersEntity> findByEmail(String email);
}
