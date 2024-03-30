package com.hamzabekkaoui.ecommerceapplication.init;

import com.hamzabekkaoui.ecommerceapplication.dto.request.*;
import com.hamzabekkaoui.ecommerceapplication.dto.response.CategoryResponse;
import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.User;
import com.hamzabekkaoui.ecommerceapplication.enums.Role;
import com.hamzabekkaoui.ecommerceapplication.repository.CategoryRepository;
import com.hamzabekkaoui.ecommerceapplication.repository.ProductRepository;
import com.hamzabekkaoui.ecommerceapplication.repository.UserRepository;
import com.hamzabekkaoui.ecommerceapplication.service.implementation.CategoryServiceImpl;
import com.hamzabekkaoui.ecommerceapplication.service.implementation.OrderServiceImpl;
import com.hamzabekkaoui.ecommerceapplication.service.implementation.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
public class CreateDataForTest {


    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            ProductServiceImpl productService,
            ProductRepository productRepository,
            CategoryServiceImpl categoryService,
            CategoryRepository categoryRepository,
            OrderServiceImpl orderService,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            Set<Role> AdminAuthorities = new HashSet<>();
            AdminAuthorities.add(Role.CUSTOMER);
            AdminAuthorities.add(Role.ADMIN);
            // create user : admin
            if (!userRepository.existsUserByMail("admin@gmail.com")) {
                User admin = User.builder()
                        .userId(UUID.randomUUID().toString())
                        .userName("admin")
                        .mail("admin@gmail.com")
                        .password(passwordEncoder.encode("password"))
                        .authorities(AdminAuthorities)
                        .build();
                userRepository.save(admin);
            }


            Set<Role> userAuthorities = new HashSet<>();
            userAuthorities.add(Role.CUSTOMER);
            // create user : customer
            if (!userRepository.existsUserByMail("customer@gmail.com")) {
                User admin = User.builder()
                        .userId(UUID.randomUUID().toString())
                        .userName("customer")
                        .mail("customer@gmail.com")
                        .password(passwordEncoder.encode("password"))
                        .authorities(userAuthorities)
                        .build();
                userRepository.save(admin);
            }
            // create user : Hamza
            if (!userRepository.existsUserByMail("hamza@gmail.com")) {
                User hamza = User.builder()
                        .userId(UUID.randomUUID().toString())
                        .userName("hamza")
                        .mail("hamza@gmail.com")
                        .password(passwordEncoder.encode("password"))
                        .authorities(userAuthorities)
                        .build();
                userRepository.save(hamza);
            }


            if(!categoryRepository.existsByCategoryName("Technology")){
                CategoryRequest categoryRequest = CategoryRequest.builder()
                        .categoryName("Technology")
                        .description("description ... ")
                        .build();
                CategoryResponse categoryResponse = categoryService.create(categoryRequest);
            }


            if(!productRepository.existsByName("laptop HP") && !productRepository.existsByName("screen")){
                ProductRequest productRequest1 = ProductRequest.builder()
                        .name("laptop HP")
                        .description("description ... ")
                        .categoryName("Technology")
                        .price(10000)
                        .quantity(100)
                        .build();

                ProductRequest productRequest2 = ProductRequest.builder()
                        .name("screen")
                        .description("description ... ")
                        .categoryName("Technology")
                        .price(10000)
                        .quantity(100)
                        .build();
                ProductResponse productN1 = productService.create(productRequest1);
                ProductResponse productN2 = productService.create(productRequest2);



                AddressRequest addressRequest = AddressRequest.builder()
                        .country("morocco")
                        .city("rabat")
                        .street("amal 4")
                        .postalCode("10000")
                        .build();

                List<OrderItemRequest> orderItemRequests = new ArrayList<>();

                orderItemRequests.add(OrderItemRequest.builder()
                        .productId(productN1.getProductId())
                        .price(productN1.getPrice())
                        .totalPrice(productN1.getPrice() * productN1.getQuantity())
                        .quantity(productN1.getQuantity())
                        .build());
                orderItemRequests.add(OrderItemRequest.builder()
                        .productId(productN2.getProductId())
                        .price(productN2.getPrice())
                        .totalPrice(productN2.getPrice() * productN2.getQuantity())
                        .quantity(productN2.getQuantity())
                        .build());

                OrderRequest hamzaOrder = OrderRequest.builder()
                        .userName("hamza")
                        .orderItemRequests(orderItemRequests)
                        .addressRequest(addressRequest)
                        .total(20000.00)
                        .build();

                orderService.create(hamzaOrder);
            }


        };


    }

}
