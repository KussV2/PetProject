package project.pet.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.pet.entity.Role;
import project.pet.entity.User;
import project.pet.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setName("admin");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setRole(Role.ROLE_ADMIN);
                userRepository.save(admin);

                User user = new User();
                user.setName("user");
                user.setPassword(encoder.encode("userpass"));
                user.setRole(Role.ROLE_USER);
                userRepository.save(user);
            }
        };
    }
}
