package com.hamzabekkaoui.ecommerceapplication.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record CouponRequest(
         Double discountPercentage,
         LocalDateTime expirationDate
) {

}
