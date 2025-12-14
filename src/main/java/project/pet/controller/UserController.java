package project.pet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.pet.DTO.UserDTO;
import project.pet.entity.User;
import project.pet.repository.UserRepository;
import project.pet.service.UserService;


@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Operation(tags = "User")
    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    /*
     * For future usage
     */
//    @Operation(tags = "User")
//    @PostMapping("/users")
//    public String createUser(@ModelAttribute User user) {
//        userService.createUser(user);
//        return "redirect:/users";
//    }
    @Operation(tags = "User")
    @PostMapping("/users")
    public String createUser(@ModelAttribute UserDTO userDTO) {
        userService.createUser(userDTO); // map DTO to entity inside service
        return "redirect:/users";
    }

    /*
     * For future usage
     */
//    @GetMapping("/users/edit/{id}")
//    public String showEditForm(@PathVariable Integer id, Model model) {
//        User user = userRepository.findById(id).orElseThrow();
//        model.addAttribute("user", user);
//        return "user-form";
//    }
    @Operation(tags = "User")
    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/users";
    }

    @Operation(tags = "User")
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @Operation(tags = "Profile")
    @GetMapping("/user-profile")
    public String getUserProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByName(userDetails.getUsername()).orElseThrow();
        model.addAttribute("user", user);
        return "user-profile";
    }

    @GetMapping("/user-profile/edit")
    public String editProfileForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByName(userDetails.getUsername()).orElseThrow();
        model.addAttribute("user", user);
        return "profile-edit";
    }

    @Operation(tags = "Profile")
    @PostMapping("/user-profile/update")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute User updatedUser) {
        //userService.updateUser(updatedUser.getId(), updatedUser);
        userService.updateProfile(userDetails.getUsername(), updatedUser);
        return "redirect:/user-profile";
    }
}
