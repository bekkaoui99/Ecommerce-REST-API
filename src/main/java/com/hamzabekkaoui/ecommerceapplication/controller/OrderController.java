package com.hamzabekkaoui.ecommerceapplication.controller;

import com.hamzabekkaoui.ecommerceapplication.dto.request.OrderRequest;

import com.hamzabekkaoui.ecommerceapplication.dto.response.OrderResponse;

import com.hamzabekkaoui.ecommerceapplication.service.implementation.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderServiceImpl orderService;



    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<OrderResponse> getPageOfOrder(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return orderService.pageOfData(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public OrderResponse getOrderByID(@PathVariable int id){
        return orderService.getByID(id);
    }


    @GetMapping("/orderCode/{orderCode}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OrderResponse getOrderByCode(@PathVariable String orderCode){
        return orderService.getByOrderCode(orderCode);
    }


    @GetMapping("/userOrders/{userName}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Page<OrderResponse> getAllOrdersUser(
            @PathVariable String userName,
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return orderService.getUserOrders(pageNumber , pageSize ,userName);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OrderResponse create(@RequestBody OrderRequest orderRequest){
            return orderService.create(orderRequest);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public OrderResponse update(
            @RequestBody OrderRequest orderRequest,
            @PathVariable int id
    ){
        return orderService.update(orderRequest , id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delete( @PathVariable int id){
        return orderService.delete(id);
    }


}
