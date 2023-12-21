package com.proyecto.apirest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.apirest.entities.User;
import com.proyecto.apirest.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
            .findUserByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));

        return new UserDetailsImpl(user);
    }
}
