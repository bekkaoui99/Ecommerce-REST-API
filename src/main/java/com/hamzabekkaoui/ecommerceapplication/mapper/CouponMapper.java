package com.hamzabekkaoui.ecommerceapplication.mapper;

import com.hamzabekkaoui.ecommerceapplication.dto.request.CouponRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CouponResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Coupon;
import org.springframework.stereotype.Service;

@Service
public class CouponMapper {

    public CouponResponse couponToCouponResponse(Coupon coupon){
        return CouponResponse.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .discountPercentage(coupon.getDiscountPercentage())
                .expirationDate(coupon.getExpirationDate())
                .isActive(coupon.getIsActive())
                .build();
    }

    public Coupon couponRequestToCoupon(CouponRequest couponRequest){
        return Coupon.builder()
                .discountPercentage(couponRequest.discountPercentage())
                .expirationDate(couponRequest.expirationDate())
                .build();
    }

}
