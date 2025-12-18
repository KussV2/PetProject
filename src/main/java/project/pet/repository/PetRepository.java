package project.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.pet.entity.Pet;
import project.pet.entity.User;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByUser(User owner);
}
