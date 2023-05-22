package com.mercadona.webapp.repository;

import com.mercadona.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //Trouver les utilisateurs par identifiant
    User findByPseudo(String pseudo);

    User findById(long id);

    //Compter les utilisateurs dans la bdd
    long count();

}
