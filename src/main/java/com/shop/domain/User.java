package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String password;
    private String email;
    private String role;
    private LocalDateTime date;

    public User() {
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
