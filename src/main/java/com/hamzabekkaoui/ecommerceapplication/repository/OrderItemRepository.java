package com.hamzabekkaoui.ecommerceapplication.repository;


import com.hamzabekkaoui.ecommerceapplication.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
