package org.sid.demodockersonarjenkins.web;

import org.sid.demodockersonarjenkins.models.Users;
import org.sid.demodockersonarjenkins.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {
    /**
     * - GET /api/users
     * - GET /api/users/{id}
     * - POST /api/users
     * - PUT /api/users/{id}
     * - DELETE /api/users/{id}
     * Users createPerson(Users user);
     *     Users updatePerson(Users user);
     *     void deletePerson(Users user);
     *     Users getPerson(Long id);
     *     List<Users> getAllPersons();
     */
    @Autowired
    UserService service;
    @PostMapping
    public ResponseEntity<Users> createuser(@RequestBody Users users){
        return  ResponseEntity.ok(service.createPerson(users));
    }
    @GetMapping()
    public ResponseEntity <List<Users>> getusers(){
        return ResponseEntity.ok(service.getAllPersons());
    }
    @GetMapping("/{id}")
    public ResponseEntity <Users> gettuser(@PathVariable Long id){
        return ResponseEntity.ok( service.getPerson(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Users> updatePerson(@RequestBody Users person, @PathVariable Long id) {
        Users personToUpdate = service.getPerson(id);
        if(personToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updatePerson(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deletePerson(@PathVariable Long id) {
        service.deletePerson(service.getPerson(id));
        return ResponseEntity.ok().build();
    }


}
