package com.hamzabekkaoui.ecommerceapplication.service.implementation;

import com.hamzabekkaoui.ecommerceapplication.dto.request.ProductRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Category;
import com.hamzabekkaoui.ecommerceapplication.entity.Product;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceAlreadyExist;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceNotFoundException;
import com.hamzabekkaoui.ecommerceapplication.mapper.ConvertEntityToDtoResponse;
import com.hamzabekkaoui.ecommerceapplication.mapper.ProductMapper;
import com.hamzabekkaoui.ecommerceapplication.repository.CategoryRepository;
import com.hamzabekkaoui.ecommerceapplication.repository.ProductRepository;
import com.hamzabekkaoui.ecommerceapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository  categoryRepository;


    @Override
    public List<ProductResponse> getAllAsList() {
        return List.of();
    }

    @Override
    public Page<ProductResponse> getAllAsPage(int pageNumber, int pageSize) {

        Page<Product> allProducts = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<ProductResponse> productResponse = allProducts.getContent().stream()
                .map(product -> productMapper.productToProductResponse(product))
                .collect(Collectors.toList());

        return new PageImpl<>(productResponse , allProducts.getPageable() , allProducts.getTotalElements() );

    }

    @Override
    public ProductResponse getByID(Integer id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("there is not product with this id : " + id));

        return productMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) throws ResourceAlreadyExist {

        boolean exists = productRepository.existsByName(productRequest.name());
        if (exists) throw new ResourceAlreadyExist("this product already exist");

        Category category = categoryRepository.findByCategoryName(productRequest.categoryName())
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .quantity(productRequest.quantity())
                .category(category)
                .build();

        Product created = productRepository.save(product);

        return productMapper.productToProductResponse(created);
    }

    @Override
    public ProductResponse update(ProductRequest productRequest, Integer id) throws ResourceNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("there is not product with this id : " + id));

        if(productRequest.name() != null)
            product.setName(product.getName());
        if(productRequest.description() != null)
            product.setDescription(productRequest.description());
        if(productRequest.price() > 0)
            product.setPrice(productRequest.price());
        if(productRequest.quantity() > 0)
            product.setQuantity(productRequest.quantity());
        if(productRequest.categoryName() != null){
            Category category = categoryRepository.findByCategoryName(productRequest.categoryName())
                    .orElseThrow(() -> new ResourceNotFoundException("there is no category with this name : " + productRequest.categoryName()));
            product.setCategory(category);
        }

        Product updated = productRepository.save(product);
        return productMapper.productToProductResponse(updated);
    }

    @Override
    public boolean delete(Integer id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("there is not product with this id : " + id));

        productRepository.delete(product);
        return true;
    }

    @Override
    public ProductResponse getByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist with this name : " + name));

        return productMapper.productToProductResponse(product);
    }

    public Page<ProductResponse> getAllProductByCategory(int pageNumber, int pageSize , String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        Page<Product> AllProductsByCategory = productRepository.findProductByCategory(PageRequest.of(pageNumber , pageSize) , category);

        List<ProductResponse> productReponseList = AllProductsByCategory.getContent().stream()
                .map(product -> productMapper.productToProductResponse(product))
                .collect(Collectors.toList());

        return new PageImpl<>(productReponseList , AllProductsByCategory.getPageable() , AllProductsByCategory.getTotalElements());
    }
}
