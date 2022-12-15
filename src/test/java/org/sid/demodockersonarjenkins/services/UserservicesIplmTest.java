package org.sid.demodockersonarjenkins.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.sid.demodockersonarjenkins.models.Users;
import org.sid.demodockersonarjenkins.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
@SpringBootTest
class UserservicesIplmTest {

    @Mock
    UserRepository personRepository;


    @InjectMocks
    UserservicesIplm personService;
    @BeforeEach
    void setUp() {
    }


    @Test
    void createPerson() {
        //Given
        Users person = new Users("malcolm","","malcolmx221@gmail.com","778593165");
        person.setId(1L);
        when(personRepository.save(any())).thenReturn(person);

        //When
        Users personAded =personService.createPerson(person);

        //Then
        assertEquals("malcolm",personAded.getNom());
        assertEquals(1,personAded.getId());
        verify(personRepository,atLeastOnce()).save(any());
    }



    @Test
    void updatePerson() {
        Users person = new Users("malcolm","X","malcolmx221@gmail.com","778593165");
        person.setId(1L);
        person.setNom("moustapha");
        when(personRepository.save(any())).thenReturn(person);

        //When
        Users personAded =personService.updatePerson(person);
        //Then
        assertEquals("moustapha",personAded.getNom());
        assertEquals(1,personAded.getId());
        verify(personRepository,atLeastOnce()).save(any());
    }

    @Test
    void deletePerson() {
        //Given
        Users person = new Users("malcolm","x","malcolmx221@gmail.com","778593165");
        person.setId(1L);
        doNothing().when(personRepository).delete(any());

        //When
        personService.deletePerson(person);

        //Then
        verify(personRepository, atLeastOnce()).delete(any());
        verifyNoMoreInteractions(personRepository);
    }

    @Test
    void getPerson() {
        Users newPerson = new Users("tonux","sammb", "tonuxndongo@gmail.com", "289483931");
        newPerson.setId(1L);
        when(personRepository.findById(1L)).thenReturn(Optional.of(newPerson));
        //When
        Users person = personService.getPerson(1L);
        //Then
        assertEquals("tonux", person.getNom());
        assertEquals(1, person.getId());
        assertEquals("tonuxndongo@gmail.com", person.getEmail());
    }

    @Test
    void getAllPersons() {
        List<Users> list = new ArrayList<Users>();
        list.add(new Users("tonux","samb","tonuxndongo@gmail.com","289483931"));
        list.add(new Users("lahad","mb","blm@gmail.com","4289358298"));
        list.add(new Users("baye","seck","bayeserigne@gmail.com","989398493831"));
        when(personRepository.findAll()).thenReturn(list);
        //When
        List<Users> personList = personService.getAllPersons();
        //Then
        assertEquals(3, personList.size());
        verify(personRepository, atLeastOnce()).findAll();
    }
}