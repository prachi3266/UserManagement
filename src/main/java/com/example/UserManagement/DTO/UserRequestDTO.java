package com.example.UserManagement.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "User Request payload")
public class UserRequestDTO {

    @Schema(description = "User's full name", example = "Prachi Jain")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @Schema(description = "User's password", example = "Password@123")
    @NotBlank(message = "Password is required")
    private String password;

    @Schema(description = "User's email", example = "prachi@gamil.com")
    @NotBlank(message = "Email can't blank")
    @Email(message = "Enter a valid email")
    private String email;

    @Schema(description = "User's age", example = "24")
    @Min(value = 18, message = "Age must be atleast 18")
    @Max(value=100,message = "Age can't exceed 100")
    private int age;



    public String getName() {
        return name;
    }


    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
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
