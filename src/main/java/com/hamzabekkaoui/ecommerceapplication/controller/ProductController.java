package com.hamzabekkaoui.ecommerceapplication.controller;


import com.hamzabekkaoui.ecommerceapplication.dto.request.ProductRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;
import com.hamzabekkaoui.ecommerceapplication.service.implementation.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;


    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Page<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return productService.getAllAsPage(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ProductResponse getProductByID(@PathVariable int id){
        return productService.getByID(id);
    }

    @PostMapping("/byName")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ProductResponse getProductByName(
            @RequestBody Map<String , String> request
            ){
        String productName = request.get("productName");
        return productService.getByName(productName);
    }

    @PostMapping("/category")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Page<ProductResponse> getAllProductByCategory(
            @RequestBody Map<String , String> request,
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        String categoryName = request.get("categoryName");
        return productService.getAllProductByCategory(pageNumber , pageSize ,categoryName);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductResponse create(@RequestBody ProductRequest productRequest){
        return productService.create(productRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductResponse update(
            @RequestBody ProductRequest productRequest,
            @PathVariable int id
    ){
        return productService.update(productRequest , id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delete( @PathVariable int id){
        return productService.delete(id);
    }
}
