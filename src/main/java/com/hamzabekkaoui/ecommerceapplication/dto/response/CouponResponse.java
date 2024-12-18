package com.hamzabekkaoui.ecommerceapplication.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record CouponResponse(
        Integer id,
        String code,
        Double discountPercentage,
        LocalDateTime expirationDate,
        boolean isActive
) {

}
