package com.medamine.todolist.Service;

import com.medamine.todolist.Configuration.JwtUtils;
import com.medamine.todolist.DTO.UserDTO;
import com.medamine.todolist.Model.Entity.Role;
import com.medamine.todolist.Model.Entity.UserEntity;
import com.medamine.todolist.Model.Exception.UserCreationException;
import com.medamine.todolist.Persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationLoginService {
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public UserDTO.PostOutput userRegistration(UserDTO.PostInput input){
        UserEntity existUser = userRepository.findByUsername(input.getUsername());
        if (existUser != null){
            throw new UserCreationException("Username already exists!");
        }
        UserEntity  newUser = UserEntity.builder()
                .name(input.getName())
                .surname(input.getSurname())
                .username(input.getUsername())
                .role(Role.USER)
                .password(passwordEncoder.encode(input.getPassword()))
                .build();
        userRepository.save(newUser);
        return UserDTO.PostOutput.builder()
                .id(newUser.getId())
                .name(newUser.getName())
                .surname(newUser.getSurname())
                .username(newUser.getUsername())
                .role(newUser.getRole())
                .build();
    }
    public ResponseEntity<?> userLogin(UserDTO.LoginRequest input){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getUsername(),
                            input.getPassword()
                    )
            );
            String token = jwtUtils.generateToken(input.getUsername());
            return ResponseEntity.ok(
                    Map.of("token", token)
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password!");
        }
    }
}