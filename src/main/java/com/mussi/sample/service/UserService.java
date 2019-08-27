package com.mussi.sample.service;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mussi.sample.entity.User;

@Stateless
@PermitAll
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public void saveUser(User user) {
        em.persist(user);
    }

    public void saveAllUsers(List<User> users) {
        users.forEach(this::saveUser);
    }

    public User findUser(User user) {
        return (User) em.createNamedQuery("User.findUserByNameAndEmail").setParameter("name", user.getName())
                .setParameter("email", user.getEmail()).getSingleResult();
    }

}
