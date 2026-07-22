/*Service Layer- Business Logic layer*/
package com.example.UserManagement.Service;

import com.example.UserManagement.Model.User;
import com.example.UserManagement.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser){

        Optional<User> user= getUserById(id);
        if(user.isEmpty()){
            return null;
        }

        User existingUser= user.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);

    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
