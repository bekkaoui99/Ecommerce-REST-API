package com.hamzabekkaoui.ecommerceapplication.mapper;

import com.hamzabekkaoui.ecommerceapplication.dto.request.CategoryRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CategoryResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryResponse categoryToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
    }

    public Category categoryRequestToCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .build();
    }

}
