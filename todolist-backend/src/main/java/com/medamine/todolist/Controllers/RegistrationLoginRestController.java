package com.medamine.todolist.Controllers;

import com.medamine.todolist.Configuration.JwtUtils;
import com.medamine.todolist.DTO.UserDTO;
import com.medamine.todolist.Model.Entity.UserEntity;
import com.medamine.todolist.Persistence.UserRepository;
import com.medamine.todolist.Service.RegistrationLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationLoginRestController {
    private final RegistrationLoginService registrationLoginService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserDTO.PostOutput registerUser(@Valid @RequestBody UserDTO.PostInput input) throws Exception {
        return registrationLoginService.userRegistration(input);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO.LoginRequest input) {
        return registrationLoginService.userLogin(input);
    }
}
