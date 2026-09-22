package com.example.UserManagement.Mapper;

import com.example.UserManagement.DTO.UserRequestDTO;
import com.example.UserManagement.DTO.UserResponseDTO;
import com.example.UserManagement.Model.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto){
        User user= new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setPassword(dto.getPassword());

        return user;
    }

    public static UserResponseDTO toResponse(User user){
        UserResponseDTO dto =new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        dto.setRole(user.getRole());

        return dto;
    }
}
