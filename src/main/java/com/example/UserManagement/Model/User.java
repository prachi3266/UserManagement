/*Model- A Class that represent a table in DB
* Basically ORM*/
package com.example.UserManagement.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be blank!")
    private String name;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email can't blank!")
    @Email(message = "Enter a valid email!")
    private String email;

    @Min(value = 18, message = "Age must be atleast 18")
    @Max(value= 100, message = "Age can't exceed 100")
    private int age;


    public long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
