package com.hamzabekkaoui.ecommerceapplication.dto.request;

import com.hamzabekkaoui.ecommerceapplication.entity.Category;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Builder
public record ProductRequest(
        String name,
        String description,
        double price,
        Integer quantity,
        String categoryName
) {


}
