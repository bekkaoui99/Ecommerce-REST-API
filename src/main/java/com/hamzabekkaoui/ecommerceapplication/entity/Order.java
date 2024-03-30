package com.hamzabekkaoui.ecommerceapplication.entity;


import com.hamzabekkaoui.ecommerceapplication.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private String orderCode;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "address_id" , referencedColumnName = "addressId")
    private Address address;

    private OrderStatus orderStatus;

    private Double total;

}
