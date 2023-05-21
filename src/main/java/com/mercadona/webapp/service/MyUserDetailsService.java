package com.mercadona.webapp.service;

import com.mercadona.webapp.model.User;
import com.mercadona.webapp.repository.UserRepository;
import com.mercadona.webapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByPseudo(username);
        if(user == null) {
            throw new UsernameNotFoundException("Le pseudo n'existe pas");
        }
        return new MyUserDetails(user);
    }
}
