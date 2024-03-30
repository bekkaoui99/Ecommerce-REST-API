package com.hamzabekkaoui.ecommerceapplication.repository;

import com.hamzabekkaoui.ecommerceapplication.entity.Order;
import com.hamzabekkaoui.ecommerceapplication.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order , Integer> {

    Optional<Order> findByOrderCode(String orderCode);
    Page<Order> findByUser(Pageable pageable ,User user);


}
