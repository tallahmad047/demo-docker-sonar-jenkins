package org.sid.demodockersonarjenkins.web;

import org.junit.jupiter.api.Test;
import org.sid.demodockersonarjenkins.models.Users;
import org.sid.demodockersonarjenkins.services.UserservicesIplm;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController personController;

    @MockBean
    UserservicesIplm personService;


    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createuser() {
    }

    @Test
    void getusers() throws  Exception{
        RequestBuilder requestBuilder  = MockMvcRequestBuilders
                .get("/api/users")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void gettuser() throws  Exception{
        Users person = new Users("malcolm","aa","malcolmx221@gmail.com","778593165");
        person.setId(1L);

        given(personService.getPerson(1L)).willReturn(person);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/users/{id}",1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is(person.getNom()))).andReturn();


        MockHttpServletResponse response = result.getResponse();


        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updatePerson() throws Exception{
        Users person = new Users("Pata","Laye","malcolmx221@gmail.com","778593165");
        person.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);



        given(personService.getPerson(person.getId())).willReturn(person);

        given(personService.updatePerson(any(Users.class))).willAnswer((invocation) ->
                invocation.getArgument(0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/users/{id}",person.getId())
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Users personSaved = objectMapper.readValue(response.getContentAsString(),Users.class);
        assertNotNull(personSaved);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(personSaved.getNom(),personSaved.getNom());
    }

    @Test
    void deletePerson()  throws Exception{
        Users person = new Users("Ousseynou","Dione","weuz221@gmail.com","778593165");
        person.setId(1L);
        given(personService.getPerson(person.getId())).willReturn(person);
        doNothing().when(personService).deletePerson(person);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/users/{id}",person.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();


        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}