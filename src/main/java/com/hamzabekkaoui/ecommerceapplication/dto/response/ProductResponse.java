package com.hamzabekkaoui.ecommerceapplication.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    private Integer productId;

    private String name;

    private String description;

    private double price;

    private Integer quantity;

    private String categoryName;
}
