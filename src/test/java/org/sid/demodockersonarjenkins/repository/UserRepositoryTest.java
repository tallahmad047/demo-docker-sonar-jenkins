package org.sid.demodockersonarjenkins.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sid.demodockersonarjenkins.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    UserRepository  personRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void createPerson(){
        Users person  =  personRepository.save(new Users ("malcolm","malcomx","malcolmx221@gmail.com","778593165"));
        assertNotNull(person);
        assertEquals("malcolm",person.getNom());
    }
    @Test
    public void getPersonById(){
        Users  personFind = personRepository.findById(2L).get();
        assertEquals(2,personFind.getId());
    }
    @Test
    public void getAllPerson(){
        List<Users> list = new ArrayList<Users >();
        list.add(new Users ("tonux","tonux","tonuxndongo@gmail.com","289483931"));
        list.add(new Users ("lahad","mbacke","blm@gmail.com","4289358298"));
        list.add(new Users ("baye","seck","bayeserigne@gmail.com","989398493831"));
        personRepository.saveAll(list);

        List<Users > personList = personRepository.findAll();

        Assertions.assertThat(personList.size()).isGreaterThan(0);
    }
    @Test
    public void updatePerson(){
        Users  person  =  personRepository.save(new Users ("malcolm","malcomx","malcolmx221@gmail.com","778593165"));
        person.setNom("mbacke");
        Users  personupdate  =  personRepository.save(person);
        assertNotNull(personupdate);
        assertEquals("mbacke",personupdate.getNom());
    }
    @Test
    public void deletePerson(){
        //Person person  =  personRepository.save(new Person("mbacke","malcolmx221@gmail.com","778593165"));
        Users  person = personRepository.findById(1L).get();
        personRepository.delete(personRepository.findById(person.getId()).get());

        Users  person1 = null;

        Optional<Users > person2 = personRepository.findByEmail(person.getEmail());

        if (person2.isPresent()){
            person1 = person2.get();
        }
        Assertions.assertThat(person1).isNull();
    }

}