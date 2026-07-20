package com.medamine.todolist.Service;


import com.medamine.todolist.Model.Entity.UserEntity;
import com.medamine.todolist.Persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("user not found!");
        }
        return new User(user.getUsername(), user.getPassword(),  Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+user.getRole().name())));
    }
}
