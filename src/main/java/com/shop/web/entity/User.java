package com.shop.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String password;
    private String role;
    private String email;
    private Boolean auth;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(String email, String password, String role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
