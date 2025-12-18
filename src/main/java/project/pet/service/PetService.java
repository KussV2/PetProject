package project.pet.service;

import org.springframework.stereotype.Service;
import project.pet.entity.Pet;
import project.pet.entity.User;
import project.pet.repository.PetRepository;
import project.pet.repository.UserRepository;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetService(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public void createPet(String username, Pet pet) {
        User owner = userRepository.findByName(username).orElseThrow();
        pet.setUser(owner);
        petRepository.save(pet);
    }

    public void deletePet(Integer petId) {
        petRepository.deleteById(petId);
    }

    public List<Pet> getUserPets(String username) {
        User user = userRepository.findByName(username).orElseThrow();
        return petRepository.findByUser(user);
    }
}