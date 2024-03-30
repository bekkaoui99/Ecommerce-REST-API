package com.hamzabekkaoui.ecommerceapplication.dto.request;

import com.hamzabekkaoui.ecommerceapplication.entity.Category;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    private String name;

    private String description;

    private double price;

    private Integer quantity;

    private String categoryName;
}
