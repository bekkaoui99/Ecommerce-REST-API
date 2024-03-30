package com.hamzabekkaoui.ecommerceapplication.repository;

import com.hamzabekkaoui.ecommerceapplication.entity.Category;
import com.hamzabekkaoui.ecommerceapplication.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product , Integer> {

    Page<Product> findProductByCategory(Pageable pageable , Category category);
    Optional<Product> findByName(String name);

    boolean existsByName(String name);
}
