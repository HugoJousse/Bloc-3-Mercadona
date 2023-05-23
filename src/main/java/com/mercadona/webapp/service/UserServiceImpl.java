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

    /**
     * Save a user and encode his password in bdd
     * @param user User
     */
    public void saveUser(User user) {

        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        userRepository.save(user);
    }

    /**
     * Edit a user and encode his password in bdd
     * @param user
     * @throws UsernameNotFoundException
     */
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

    /**
     * check if there is already at least one user registered
     * @return boolean
     */
    @Override
    public boolean isUsersRegistered() {
        if(userRepository.count() == 0){
            return false;
        }
        return true;
    }

    /**
     * Check if the pseudo already exist
     * @param pseudo String
     * @return boolean
     */
    public boolean isPseudoExist(String pseudo) {
        User user = userRepository.findByPseudo(pseudo);
        if(user != null) {
           return true;
        }
        return false;
    }

    /**
     * Get a user by a pseudo
     * @param pseudo String
     * @return User
     */
    public User getUserByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo);
    }

    /**
     * Get a user by an Id
     * @param id long
     * @return User
     */
    public User getUserById(long id) {
        return userRepository.findById(id);
    }
}
