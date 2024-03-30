package com.hamzabekkaoui.ecommerceapplication.service;

import com.hamzabekkaoui.ecommerceapplication.dto.request.ProductRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService extends CrudService<ProductRequest , ProductResponse , Integer>{

    ProductResponse getByName(String name);
    Page<ProductResponse> getAllProductByCategory(int pageNumber, int pageSize ,String category);
}
