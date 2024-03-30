package com.hamzabekkaoui.ecommerceapplication.service.implementation;

import com.hamzabekkaoui.ecommerceapplication.dto.request.CategoryRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CategoryResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Category;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceAlreadyExist;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceNotFoundException;
import com.hamzabekkaoui.ecommerceapplication.mapper.CategoryMapper;
import com.hamzabekkaoui.ecommerceapplication.repository.CategoryRepository;
import com.hamzabekkaoui.ecommerceapplication.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;



    @Override
    public Page<CategoryResponse> pageOfData(int pageNumber, int pageSize) {

        Page<Category> allCategories = categoryRepository.findAll(PageRequest.of(pageNumber, pageSize));

        List<CategoryResponse> categoryResponseList = allCategories.getContent().stream()
                .map(category -> categoryMapper.categoryToCategoryResponse(category))
                .collect(Collectors.toList());

        return new PageImpl<>(categoryResponseList , allCategories.getPageable() , allCategories.getTotalElements());

    }

    @Override
    public CategoryResponse getByID(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("there is no category with this id : " + id));
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {

        boolean exists = categoryRepository.existsByCategoryName(categoryRequest.categoryName());
        if(exists) throw new ResourceAlreadyExist("category with this name already exist :(");

        Category category = Category.builder()
                .categoryName(categoryRequest.categoryName())
                .description(categoryRequest.description())
                .build();

        Category created = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(created);

    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("there is no category with this id : " + id));
        if(categoryRequest.categoryName() != null)
            category.setCategoryName(category.getCategoryName());
        if(categoryRequest.description() != null)
            category.setDescription(category.getDescription());
        Category updated = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(updated);

    }

    @Override
    public boolean delete(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("there is no category with this id : " + id));
        categoryRepository.delete(category);
        return true;
    }
}
