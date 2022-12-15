package org.sid.demodockersonarjenkins.services;

import org.apache.catalina.User;
import org.sid.demodockersonarjenkins.models.Users;
import org.sid.demodockersonarjenkins.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserservicesIplm implements UserService {
    @Autowired
    UserRepository repository;
    Logger logger = LoggerFactory.getLogger(UserservicesIplm.class);

    @Override
    public Users createPerson(Users user) {
        logger.info("Person created");
        return  repository.save(user);
    }

    @Override
    public Users updatePerson(Users user) {
        logger.info("Person updated");
        return repository.save(user);
    }

    @Override
    public void deletePerson(Users user) {
        logger.info("user supprimer");
        repository.delete(user);

    }

    @Override
    public Users getPerson(Long id) {
        logger.info("Person found");
        Optional<Users> response =  repository.findById(id);
        return response.orElse(null);

    }


    @Override
    public List<Users> getAllPersons() {
        logger.info("liste de tout les personne");
        return repository.findAll();
    }
}
