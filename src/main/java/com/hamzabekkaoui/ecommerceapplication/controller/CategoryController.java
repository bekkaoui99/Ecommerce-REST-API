package com.hamzabekkaoui.ecommerceapplication.controller;

import com.hamzabekkaoui.ecommerceapplication.dto.request.CategoryRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CategoryResponse;
import com.hamzabekkaoui.ecommerceapplication.service.implementation.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Page<CategoryResponse> getPageOfCategory(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return categoryService.getAllAsPage(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public CategoryResponse getCategoryByID(@PathVariable int id){
        return categoryService.getByID(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse create(@RequestBody CategoryRequest categoryRequest){
        return categoryService.create(categoryRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse update(
            @RequestBody CategoryRequest categoryRequest ,
            @PathVariable int id
    ){
        return categoryService.update(categoryRequest , id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delete( @PathVariable int id){
        return categoryService.delete(id);
    }
}
