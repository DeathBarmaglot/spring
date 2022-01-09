package com.shop.store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    private String name;
    private String description;
    private int price;
    @CreatedDate
    private LocalDateTime date;

    public Product(String name, String description, int price, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
    }
}
