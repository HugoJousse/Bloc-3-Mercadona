package com.mercadona.webapp.service;

import com.mercadona.webapp.model.User;
import com.mercadona.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {
        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public boolean isUsersRegistered() {
        if(userRepository.count() == 0){
            return false;
        }
        return true;
    }

    public boolean isPseudoExist(String pseudo) {
        User user = userRepository.findByPseudo(pseudo);
        if(user != null) {
           return true;
        }
        return false;
    }

    public User getUserByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo);
    }
}
