package com.hamzabekkaoui.ecommerceapplication.mapper;


import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {


    public ProductResponse productToProductResponse(Product product){

        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryName(product.getCategory().getCategoryName())
                .build();
    }


}
