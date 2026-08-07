/*Service Layer- Business Logic layer*/
package com.example.UserManagement.Service;

import com.example.UserManagement.DTO.UserRequestDTO;
import com.example.UserManagement.DTO.UserResponseDTO;
import com.example.UserManagement.Mapper.UserMapper;
import com.example.UserManagement.Model.User;
import com.example.UserManagement.Repository.UserRepository;
import com.example.UserManagement.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO){

        User user= UserMapper.toEntity(userRequestDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user= userRepository.save(user);

        UserResponseDTO response=UserMapper.toResponse(user);

        return response;

    }

    public List<UserResponseDTO> getAllUsers(){
        List<User> users= userRepository.findAll();
        return users.stream().map(user->UserMapper.toResponse(user)).toList();
    }

    public UserResponseDTO getUserById(Long id){
        Optional<User> user= userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found with id: "+ id);
        }
        UserResponseDTO response= UserMapper.toResponse(user.get());

        return response;

    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser){

        Optional<User> existingUser= userRepository.findById(id);
        if(existingUser.isEmpty()){
            throw new UserNotFoundException("User not found with id: "+id);
        }

        User user=existingUser.get();
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());

        user= userRepository.save(user);

        UserResponseDTO updatedUserResponse= UserMapper.toResponse(user);
        return updatedUserResponse;
    }

    public void deleteUser(Long id){

        userRepository.deleteById(id);
    }

}
