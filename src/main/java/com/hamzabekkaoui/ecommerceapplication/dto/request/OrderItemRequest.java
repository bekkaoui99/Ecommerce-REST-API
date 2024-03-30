package com.hamzabekkaoui.ecommerceapplication.dto.request;

import com.hamzabekkaoui.ecommerceapplication.entity.Order;
import com.hamzabekkaoui.ecommerceapplication.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemRequest {


    private Integer productId;

    private Integer quantity;

    private Double price;

    private Double totalPrice;
}
