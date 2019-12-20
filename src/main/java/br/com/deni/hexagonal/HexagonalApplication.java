package br.com.deni.hexagonal;


import br.com.deni.hexagonal.adapters.ReceiverAdapter;
import br.com.deni.hexagonal.adapters.SenderAdapter;
import br.com.deni.hexagonal.core.domain.User;
import br.com.deni.hexagonal.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class HexagonalApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SenderAdapter sender;

    @Autowired
    private ReceiverAdapter receiver;

    public static void main(String[] args) {
        SpringApplication.run(HexagonalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        sender.send("Hello Spring JMS ActiveMQ!");
        User john = new User(null, "John Yellow");
        userRepository.save(john);
        User maria = new User(null, "Maria Brown");
       User alex = new User(null, "Alex Green");
       User bob = new User(null, "Bob Grey");

       userRepository.saveAll(Arrays.asList(maria, alex, bob));


    }


}

