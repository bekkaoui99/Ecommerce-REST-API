package com.hamzabekkaoui.ecommerceapplication.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {

    private Integer categoryId;

    private String categoryName;

    private String description;
}
