package com.hamzabekkaoui.ecommerceapplication.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemResponse {

    private Integer OrderItemId;

    private Integer OrderId;

    private ProductResponse productResponse;

    private Integer quantity;

    private Double price;

    private Double totalPrice;
}
