package com.hamzabekkaoui.ecommerceapplication.mapper;


import com.hamzabekkaoui.ecommerceapplication.dto.response.OrderItemResponse;

import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;

import com.hamzabekkaoui.ecommerceapplication.entity.OrderItem;

import org.springframework.stereotype.Service;

@Service
public class OrderItemMapper {



    public OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem){
        return OrderItemResponse.builder()
                .OrderItemId(orderItem.getOrderItemId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .totalPrice(orderItem.getTotalPrice())
                .productResponse(
                        ProductResponse.builder()
                                .productId(orderItem.getProduct().getProductId())
                                .name(orderItem.getProduct().getName())
                                .description(orderItem.getProduct().getDescription())
                                .price(orderItem.getProduct().getPrice())
                                .quantity(orderItem.getProduct().getQuantity())
                                .categoryName(orderItem.getProduct().getCategory().getCategoryName())
                                .build()
                )
                .build();
    }




}
