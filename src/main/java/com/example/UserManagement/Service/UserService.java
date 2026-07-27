/*Service Layer- Business Logic layer*/
package com.example.UserManagement.Service;

import com.example.UserManagement.DTO.UserRequestDTO;
import com.example.UserManagement.DTO.UserResponseDTO;
import com.example.UserManagement.Model.User;
import com.example.UserManagement.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO){
        User user= new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setAge(userRequestDTO.getAge());

        user= userRepository.save(user);

        UserResponseDTO response= new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAge(user.getAge());

        return response;



    }

    public List<UserResponseDTO> getAllUsers(){
        List<User> users= userRepository.findAll();
        List<UserResponseDTO> userResponseList= new ArrayList<>();
        for(User user: users){
            UserResponseDTO responseDTO= new UserResponseDTO();
            responseDTO.setId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setAge(user.getAge());
            userResponseList.add(responseDTO);
        }
        return userResponseList;
    }

    public UserResponseDTO getUserById(Long id){
        Optional<User> user= userRepository.findById(id);
        if(user.isEmpty()){
            return null;
        }
        UserResponseDTO response= new UserResponseDTO();
        response.setId(user.get().getId());
        response.setName(user.get().getName());
        response.setEmail(user.get().getEmail());
        response.setAge(user.get().getAge());

        return response;

    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser){

        Optional<User> existingUser= userRepository.findById(id);
        if(existingUser.isEmpty()){
            return null;
        }

        User user= existingUser.get();
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());

        user= userRepository.save(user);

        UserResponseDTO updatedUserResponse= new UserResponseDTO();
        updatedUserResponse.setId(user.getId());
        updatedUserResponse.setName(user.getName());
        updatedUserResponse.setEmail(user.getEmail());
        updatedUserResponse.setAge(user.getAge());
        return updatedUserResponse;
    }

    public void deleteUser(Long id){

        userRepository.deleteById(id);
    }

}
