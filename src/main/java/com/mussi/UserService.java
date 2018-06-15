package com.mussi;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public void saveUser(User user) {
        em.persist(user);
    }

    public User findUser(User user) {
        return (User) em.createNamedQuery("User.findUserByNameAndEmail")
                .setParameter("name", user.getName())
                .setParameter("email", user.getEmail())
                .getSingleResult();
    }

}