package com.hamzabekkaoui.ecommerceapplication.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequest {

    private String categoryName;

    private String description;
}
