package com.shop.store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long products_id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roles;
    private Boolean locked = false;
    private Boolean enabled = false;
    @CreatedDate
    private LocalDateTime date;

    public User(String username, String email, String password, Role roles, LocalDateTime date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.date = date;

    }

    public User(String username, String email, String password, LocalDateTime date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
    }
}
