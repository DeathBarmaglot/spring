package com.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@Table(name = "foods")
public class Food {
    private long id;
    private String name;
    private String comment;
    private String email;
    private int price;
    private LocalDateTime date;
}
