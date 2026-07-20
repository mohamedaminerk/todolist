package com.medamine.todolist.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "users")
@Entity
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks;


    @Enumerated(EnumType.STRING)
    private Role role ;
}
