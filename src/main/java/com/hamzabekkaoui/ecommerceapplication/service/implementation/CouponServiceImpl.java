package com.hamzabekkaoui.ecommerceapplication.service.implementation;

import com.hamzabekkaoui.ecommerceapplication.dto.request.CouponRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CouponResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Coupon;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceAlreadyExist;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceNotFoundException;
import com.hamzabekkaoui.ecommerceapplication.mapper.CouponMapper;
import com.hamzabekkaoui.ecommerceapplication.repository.CouponRepository;
import com.hamzabekkaoui.ecommerceapplication.service.CouponService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    private static final String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = BASE62_ALPHABET.length();

    public CouponServiceImpl(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    // Convert a number to Base62
    public static String encodeBase62(long number) {
        StringBuilder encoded = new StringBuilder();
        while (number > 0) {
            encoded.insert(0, BASE62_ALPHABET.charAt((int) (number % BASE)));
            number /= BASE;
        }
        return encoded.toString();
    }

    public static String generateCouponCode() {
        long timestamp = System.currentTimeMillis();  // You can also use a unique sequence number here
        return encodeBase62(timestamp);
    }

    @Override
    public List<CouponResponse> getAllAsList() {
        return this.couponRepository.findAll()
                .stream()
                .map(couponMapper::couponToCouponResponse)
                .toList();
    }

    @Override
    public Page<CouponResponse> getAllAsPage(int pageNumber, int pageSize) {
        Page<Coupon> allCouponsAsAPage = this.couponRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<CouponResponse> couponResponseList = allCouponsAsAPage.getContent()
                .stream()
                .map(this.couponMapper::couponToCouponResponse)
                .toList();

        return new PageImpl<>(couponResponseList , allCouponsAsAPage.getPageable() , allCouponsAsAPage.getTotalElements());
    }

    @Override
    public CouponResponse getByID(Integer couponId) throws ResourceNotFoundException {
        return this.couponRepository.findById(couponId)
                .map(couponMapper::couponToCouponResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon does not exist with this Id : " + couponId));

    }

    @Override
    public CouponResponse create(CouponRequest couponRequest) throws ResourceAlreadyExist {
        Coupon coupon = this.couponMapper.couponRequestToCoupon(couponRequest);
        coupon.setCode(generateCouponCode());
        coupon.setIsActive(true);

        Coupon createdCoupon = this.couponRepository.save(coupon);

        return this.couponMapper.couponToCouponResponse(createdCoupon);
    }

    @Override
    public CouponResponse update(CouponRequest couponRequest, Integer couponId) throws ResourceNotFoundException {
        Coupon coupon = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon does not exist with this Id : " + couponId));
        if(couponRequest.expirationDate() != null && LocalDateTime.now().isAfter(couponRequest.expirationDate())){
            coupon.setExpirationDate(couponRequest.expirationDate());
        }
        if(couponRequest.discountPercentage() != null ){
            if (couponRequest.discountPercentage() < 0)
                throw new IllegalArgumentException("Discount must be positive between 0% and 100%");
            if (couponRequest.discountPercentage() > 100)
                throw new IllegalArgumentException("Discount can not be bigger than 100%");
            coupon.setDiscountPercentage(couponRequest.discountPercentage());
        }
        Coupon updatedCoupon = this.couponRepository.save(coupon);

        return this.couponMapper.couponToCouponResponse(updatedCoupon);
    }

    @Override
    public boolean delete(Integer couponId) throws ResourceNotFoundException {
        Coupon coupon = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon does not exist with this Id : " + couponId));
        couponRepository.delete(coupon);
        return true;
    }

    @Override
    public CouponResponse getCouponByCode(String couponCode) {
        return this.couponRepository.findByCode(couponCode)
                .map(couponMapper::couponToCouponResponse)
                .orElseThrow(() -> new IllegalArgumentException("Coupon code is not valid"));
    }

}
