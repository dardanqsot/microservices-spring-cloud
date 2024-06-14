package com.dardan.microservices.userservice;

import com.dardan.microservices.userservice.model.entity.UserEntity;
import com.dardan.microservices.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class UserServiceApplication implements CommandLineRunner {

    /*Primera forma de inyección*/
    /*@Autowired
	private UserRepository userRepository;*/


    /*Segunda forma de inyección*/
    /* private final UserRepository userRepository;

    public UserServiceApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    /*Tercera forma de inyección*/
    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        userRepository.save(UserEntity.builder()
                .name("Darwin")
                .lastname("Quispe")
                .email("springcloud@gmail.com")
                .username("dardan")
                .password("dardan")
                .roles(new String[]{"ROLE_USER"})
                .build());

        userRepository.save(UserEntity.builder()
                .name("Daniel")
                .lastname("Soto")
                .email("admin@gmail.com")
                .username("dardanqsot")
                .password("dardanqsot")
                .roles(new String[]{"ROLE_ADMIN"})
                .build());

    }

}
