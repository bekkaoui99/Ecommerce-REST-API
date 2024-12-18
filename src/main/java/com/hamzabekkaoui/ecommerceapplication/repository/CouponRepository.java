package com.hamzabekkaoui.ecommerceapplication.repository;

import com.hamzabekkaoui.ecommerceapplication.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByCode(String couponCode);

}
