package com.mercadona.webapp.service;

import com.mercadona.webapp.model.User;
import com.mercadona.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void editUser(User user) throws UsernameNotFoundException {
        User editingUser = getUserByPseudo(user.getPseudo());
        if(editingUser.getPseudo() == null){
            throw new UsernameNotFoundException("User not exist");
        }
        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        editingUser.setPassword(password);
        userRepository.save(editingUser);
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

    public User getUserById(long id) {
        return userRepository.findById(id);
    }
}
