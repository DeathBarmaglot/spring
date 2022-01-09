package com.shop.store.repository;

import com.shop.store.entity.Product;
import com.shop.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
