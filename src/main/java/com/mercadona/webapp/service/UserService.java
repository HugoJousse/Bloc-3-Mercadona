package com.mercadona.webapp.service;

import com.mercadona.webapp.model.User;

public interface UserService {


    public void saveUser(User user);
    public boolean isPseudoExist(String pseudo);
    public boolean isUsersRegistered();

    public User getUserByPseudo(String pseudo);
}
