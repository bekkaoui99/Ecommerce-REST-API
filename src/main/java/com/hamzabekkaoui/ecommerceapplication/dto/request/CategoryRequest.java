package com.hamzabekkaoui.ecommerceapplication.dto.request;

import lombok.*;


@Builder
public record CategoryRequest(
        String categoryName,
        String description
) {

}
