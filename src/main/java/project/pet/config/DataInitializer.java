package project.pet.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.pet.entity.Pet;
import project.pet.entity.Role;
import project.pet.entity.User;
import project.pet.repository.PetRepository;
import project.pet.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder, PetRepository petRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setName("admin");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setRole(Role.ROLE_ADMIN);
                userRepository.save(admin);

                Pet adminPet1 = new Pet();
                adminPet1.setName("Tioma");
                adminPet1.setType("Cat");
                adminPet1.setUser(admin);

                admin.getPets().add(adminPet1);

                petRepository.saveAll(admin.getPets());

                User user = new User();
                user.setName("user");
                user.setPassword(encoder.encode("userpass"));
                user.setRole(Role.ROLE_USER);
                userRepository.save(user);

                Pet userPet1 = new Pet();
                userPet1.setName("Buddy");
                userPet1.setType("Dog");
                userPet1.setUser(user);

                Pet userPet2 = new Pet();
                userPet2.setName("Mittens");
                userPet2.setType("Cat");
                userPet2.setUser(user);

                user.getPets().add(userPet1);
                user.getPets().add(userPet2);

                petRepository.saveAll(user.getPets());
            }
        };
    }
}

