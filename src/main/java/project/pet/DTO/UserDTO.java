package project.pet.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.pet.entity.Role;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String password;
    private Role role;
}
