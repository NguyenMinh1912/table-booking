package com.tableorder.tableorder.config;

import com.tableorder.tableorder.entity.Role;
import com.tableorder.tableorder.entity.UserEntity;
import com.tableorder.tableorder.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;

    public InitDataConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = UserEntity.builder()
                .username("user")
                .password("user")
                .roles(Arrays.asList(Role.GUEST))
                .build();

        UserEntity admin = UserEntity.builder()
                .username("admin")
                .password("admin")
                .roles(Arrays.asList(Role.ADMIN))
                .build();

        userRepository.save(user);
        userRepository.save(admin);

    }
}
