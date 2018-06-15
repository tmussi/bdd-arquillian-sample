package com.mussi;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "User.findUserByNameAndEmail", query = "SELECT u FROM User u WHERE u.name = :name AND u.email = :email") })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "SEQ_USERS", allocationSize = 1, name = "SEQ_USERS")
    private BigDecimal id;
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "; name=" + this.name + "; email=" + this.email + "; password="
                + this.password + "];";
    }

}
