package com.hamzabekkaoui.ecommerceapplication.controller;


import com.hamzabekkaoui.ecommerceapplication.dto.request.ProductRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;
import com.hamzabekkaoui.ecommerceapplication.service.implementation.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;


    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Page<ProductResponse> getPageOfProduct(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return productService.pageOfData(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ProductResponse getProductByID(@PathVariable int id){
        return productService.getByID(id);
    }

    @GetMapping("/ByName/{name}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ProductResponse getProductByName(
            @PathVariable(name = "name") String name
    ){
        return productService.getByName(name);
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Page<ProductResponse> getAllProductByCategory(
            @PathVariable(name = "category") String category,
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return productService.getAllProductByCategory(pageNumber , pageSize ,category);
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
