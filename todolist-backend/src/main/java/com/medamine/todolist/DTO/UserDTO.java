package com.medamine.todolist.DTO;

import com.medamine.todolist.Model.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDTO {
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static  class PostInput{
        @NotBlank
        String name;
        @NotBlank
        String surname;
        @NotBlank
        String username;
        @NotBlank
        String password;
        Role role;

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class LoginRequest {
        String username;
        String password;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static  class PostOutput{
        Long id;
        String name;
        String surname;
        String username;
        Role role;
    }
}
