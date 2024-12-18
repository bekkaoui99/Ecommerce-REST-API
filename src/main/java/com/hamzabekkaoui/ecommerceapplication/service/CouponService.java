package com.hamzabekkaoui.ecommerceapplication.service;


import com.hamzabekkaoui.ecommerceapplication.dto.request.CouponRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CouponResponse;


public interface CouponService extends CrudService<CouponRequest, CouponResponse, Integer>{

    CouponResponse getCouponByCode(String couponCode);

}
