package org.sid.demodockersonarjenkins;

import org.sid.demodockersonarjenkins.models.Users;
import org.sid.demodockersonarjenkins.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoDockerSonarJenkinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDockerSonarJenkinsApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserService service){
        return args ->
            Stream.of("ahmad","awa","saliou").forEach(nom->{
                Users users=new Users();
                users.setNom(nom);
                users.setEmail(nom +"@gmail.com");
                users.setPrenom(nom);
                users.setPassword(nom + "1234");
                service.createPerson(users);
            });

    }

}
