package com.example.UserManagement.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User details returned by API")
public class UserResponseDTO {

    @Schema(description = "User's unique ID", example = "1")
    private Long id;
    @Schema(description = "User's full name", example = "Prachi Jain")
    private String name;
    @Schema(description = "User's email", example = "prachi@gmail.com")
    private String email;
    @Schema(description = "User's age", example="24")
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age= age;
    }
}
