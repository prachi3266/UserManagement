/* Cotroller- API creation
* @RestController- Marks this class as API Controller and returns JSON not HTML
* @RequestMapping- Base URL for all APIs
* @PostMapping- API to create user
* @GetMapping- API to get all users
* @RequestBody- Converts JSON into JAVA Object*/
package com.example.UserManagement.Controller;

import com.example.UserManagement.Model.User;
import com.example.UserManagement.Service.UserService;
import com.example.UserManagement.UserManagementApplication;
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
     public User createUser(@RequestBody User user){
         return userService.saveUser(user);
     }

     @GetMapping
     public List<User> getAllUsers(){
         return userService.getAllUser();
     }

     @GetMapping("/{id}")
     public ResponseEntity<User> getUserById(@PathVariable Long id){

         Optional<User> user= userService.getUserById(id);

         if(user.isPresent()){
             return  ResponseEntity.ok(user.get());
         }

         return ResponseEntity.notFound().build();
     }

     @PutMapping("/{id}")
     public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user){

         User updatedUser= userService.updateUser(id, user);

         if(updatedUser== null){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(updatedUser);
     }

}