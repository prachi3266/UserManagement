/* Cotroller- API creation
* @RestController- Marks this class as API Controller and returns JSON not HTML
* @RequestMapping- Base URL for all APIs
* @PostMapping- API to create user
* @GetMapping- API to get all users
* @RequestBody- Converts JSON into JAVA Object*/
package com.example.UserManagement.Controller;

import com.example.UserManagement.DTO.RoleUpdateDTO;
import com.example.UserManagement.DTO.UserRequestDTO;
import com.example.UserManagement.DTO.UserResponseDTO;
import com.example.UserManagement.Model.User;
import com.example.UserManagement.Service.UserService;
import com.example.UserManagement.UserManagementApplication;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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

     @Operation(summary = "Create a new user")
     @PostMapping
     public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO user){
         return userService.saveUser(user);
     }

     @Operation(summary = "Get all users")
     @GetMapping
     public List<UserResponseDTO> getAllUsers(){
         return userService.getAllUsers();
     }

     @Operation(summary = "Get user by ID")
     @GetMapping("/{id}")
     public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id, Authentication authentication){

         String loggedInEmail= authentication.getName();

         boolean isAdmin= authentication.getAuthorities()
                 .stream()
                 .anyMatch(authority ->
                         authority.getAuthority().equals("ROLE_ADMIN"));

         boolean isOwner= userService.isOwner(id, loggedInEmail);

         if(!isAdmin && !isOwner){
             throw new AccessDeniedException("You do not have permission to access this user");
         }

         UserResponseDTO userResponseDTO= userService.getUserById(id);

         return ResponseEntity.ok(userResponseDTO);
     }

     @Operation(summary = "Update a user by ID")
     @PutMapping("/{id}")
     public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody UserRequestDTO updatedUser, Authentication authentication){

         String loggedInEmail= authentication.getName();

         boolean isOwner= userService.isOwner(id, loggedInEmail);

         if(!isOwner){
             throw new AccessDeniedException("You can only update your own profile");
         }


         UserResponseDTO response= userService.updateUser(id, updatedUser);

         return ResponseEntity.ok(response);
     }

     @PatchMapping("/{id}/role")
     public ResponseEntity<UserResponseDTO> updateRole(@PathVariable Long id, @RequestBody RoleUpdateDTO roleUpdateDTO){

         UserResponseDTO response= userService.updateRole(id, roleUpdateDTO.getRole());

         return ResponseEntity.ok(response);

     }

     @Operation(summary = "Delete a user by ID")
     @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id, Authentication authentication){

         String loggedInEmail= authentication.getName();

         boolean isAdmin= authentication.getAuthorities()
                 .stream()
                 .anyMatch( authority -> authority.getAuthority().equals("ROLE_ADMIN"));

         boolean isOwner= userService.isOwner(id, loggedInEmail);

         if(!isAdmin && !isOwner){
             throw new AccessDeniedException("You do not have permission to delete this user");
         }
         UserResponseDTO deletedUser= userService.getUserById(id);
         userService.deleteUser(id);
         return ResponseEntity.ok(deletedUser);
     }

}