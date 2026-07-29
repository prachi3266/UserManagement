/* Cotroller- API creation
* @RestController- Marks this class as API Controller and returns JSON not HTML
* @RequestMapping- Base URL for all APIs
* @PostMapping- API to create user
* @GetMapping- API to get all users
* @RequestBody- Converts JSON into JAVA Object*/
package com.example.UserManagement.Controller;

import com.example.UserManagement.DTO.UserRequestDTO;
import com.example.UserManagement.DTO.UserResponseDTO;
import com.example.UserManagement.Model.User;
import com.example.UserManagement.Service.UserService;
import com.example.UserManagement.UserManagementApplication;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
     public UserController(UserService userService){
        this.userService= userService;
     }

     @PostMapping
     public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO user){
         return userService.saveUser(user);
     }

     @GetMapping
     public List<UserResponseDTO> getAllUsers(){
         return userService.getAllUsers();
     }

     @GetMapping("/{id}")
     public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){

         UserResponseDTO userResponseDTO= userService.getUserById(id);

         return ResponseEntity.ok(userResponseDTO);
     }

     @PutMapping("/{id}")
     public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody UserRequestDTO updatedUser){

         UserResponseDTO response= userService.updateUser(id, updatedUser);

         return ResponseEntity.ok(response);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id){
         UserResponseDTO deletedUser= userService.getUserById(id);
         userService.deleteUser(id);
         return ResponseEntity.ok(deletedUser);


     }

}