package com.hamzabekkaoui.ecommerceapplication.dto.request;

import com.hamzabekkaoui.ecommerceapplication.entity.Order;
import com.hamzabekkaoui.ecommerceapplication.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Builder
public record OrderItemRequest(
        Integer productId,
        Integer quantity,
        Double price,
        Double totalPrice
) {

}
