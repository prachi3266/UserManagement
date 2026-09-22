package com.example.UserManagement.Controller;


import com.example.UserManagement.DTO.LoginRequestDTO;
import com.example.UserManagement.DTO.LoginResponseDTO;
import com.example.UserManagement.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequestDTO request){
        try{
            return ResponseEntity.ok(authService.login(request));
        }catch (AuthenticationException ex){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "error", "Unauthorized",
                            "message", "Invalid email or password"
                    ));
        }
    }

}
