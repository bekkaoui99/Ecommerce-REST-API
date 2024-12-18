package com.hamzabekkaoui.ecommerceapplication.controller;

import com.hamzabekkaoui.ecommerceapplication.dto.request.CouponRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CouponResponse;
import com.hamzabekkaoui.ecommerceapplication.service.CouponService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/coupons")
public class CouponController {


    private final CouponService couponService;


    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CouponResponse createCoupon(@RequestBody CouponRequest couponRequest){
        return this.couponService.create(couponRequest);
    }

    @PutMapping("/{couponId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CouponResponse updateCoupon(@RequestBody CouponRequest couponRequest, @PathVariable Integer couponId){
        return this.couponService.update(couponRequest , couponId);
    }


    @DeleteMapping("/{couponId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteCoupon(@PathVariable Integer couponId){
        return this.couponService.delete(couponId);
    }


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CouponResponse> getAllCouponsAsList(){
        return this.couponService.getAllAsList();
    }


    @GetMapping("/page")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<CouponResponse> getAllCouponsAsPage(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") int pageSize
    ){
        return this.couponService.getAllAsPage(pageNumber, pageSize);
    }

    @GetMapping("/code")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CouponResponse getAllCouponsAsPage(
            @RequestParam(name = "couponCode") String couponCode
    ){
        return this.couponService.getCouponByCode(couponCode);
    }


}
