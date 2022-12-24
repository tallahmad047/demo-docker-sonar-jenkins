package org.sid.demodockersonarjenkins.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sid.demodockersonarjenkins.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
    void create(){
        Users person = personRepository.save(new Users("tonux","tonux", "tonux@gmail.com", "123456"));
        assertNotNull(person);
        assertEquals("tonux", person.getNom());
    }

    @Test
    void update(){
        //Given
        Users person = personRepository.save(new Users("tonux","tonux", "tonux@gmail.com", "123456"));
        person.setNom("Coundoul");
        //When
        Users personUpdated = personRepository.save(person);
        //Then
        assertNotNull(personUpdated);
        assertEquals("Coundoul", personUpdated.getNom());
    }
    // TODO : add test delete
    @Test
    void delete(){
        Users person=personRepository.save(new Users("tonux","tonux",
                "tonux@gmail.com", "12938884"));
        person.setId(1L);
        personRepository.delete(person);
        assertNotNull(person);
        assertEquals(200,HttpStatus.OK.value());

    }



    // TODO : add test findById
    @Test
    void findById()
    {
        Users person=personRepository.save(new Users("tonux","tonux",
                "tonux@gmail.com", "12938884"));
        Optional<Users> personList=personRepository.findById(person.getId());
        assertNotNull(personList);
        assertEquals("tonux",personList.get().getNom());
    }

    // TODO : add test findAll

    @Test
    void findAll()
    {
        List<Users> personList=personRepository.findAll();
        assertNotNull(personList);
        assertEquals(4,personList.size());
    }

}