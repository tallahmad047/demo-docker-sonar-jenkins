package org.sid.demodockersonarjenkins.services;


import org.sid.demodockersonarjenkins.models.Users;

import java.util.List;

public interface UserService {
    Users createPerson(Users user);
    Users updatePerson(Users user);
    void deletePerson(Users user);
    Users getPerson(Long id);
    List<Users> getAllPersons();
}
