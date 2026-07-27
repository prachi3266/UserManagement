package com.example.UserManagement.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {

    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotBlank(message = "Email can't blank")
    @Email(message = "Enter a valid email")
    private String email;
    @Min(value = 18, message = "Age must be atleast 18")
    @Max(value=100,message = "Age can't exceed 100")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
