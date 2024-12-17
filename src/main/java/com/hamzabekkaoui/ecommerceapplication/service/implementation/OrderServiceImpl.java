package com.hamzabekkaoui.ecommerceapplication.service.implementation;

import com.hamzabekkaoui.ecommerceapplication.dto.request.OrderRequest;

import com.hamzabekkaoui.ecommerceapplication.dto.response.OrderResponse;

import com.hamzabekkaoui.ecommerceapplication.entity.*;
import com.hamzabekkaoui.ecommerceapplication.enums.OrderStatus;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceAlreadyExist;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceNotFoundException;
import com.hamzabekkaoui.ecommerceapplication.mapper.AddressMapper;
import com.hamzabekkaoui.ecommerceapplication.mapper.OrderMapper;
import com.hamzabekkaoui.ecommerceapplication.repository.*;
import com.hamzabekkaoui.ecommerceapplication.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;
    private final OrderMapper orderMapper;
    private final AddressMapper addressMapper;



    @Override
    public Page<OrderResponse> pageOfData(int pageNumber, int pageSize) {

        Page<Order> orders = orderRepository.findAll(PageRequest.of(pageNumber, pageSize));

        List<OrderResponse> orderResponse = orders.getContent().stream()
                .map(order -> orderMapper.orderToOrderResponse(order))
                .collect(Collectors.toList());

        return new PageImpl<>(orderResponse , orders.getPageable() , orders.getTotalElements());
    }

    @Override
    public OrderResponse getByID(Integer id) throws ResourceNotFoundException {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order doesn't exist"));

        return orderMapper.orderToOrderResponse(order);
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist with this id : " + id));

    }

    @Override
    @Transactional
    public OrderResponse create(OrderRequest orderRequest) throws ResourceAlreadyExist {

        // create address where the order will be delivered
        Address address = addressMapper.addressRequestToAddress(orderRequest.addressRequest());
        addressRepository.save(address);

        // get the user who wants to order some products
        User user = userRepository.findByUserName(orderRequest.userName())
                .orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));


        // create an order
        Order order = Order.builder()
                .orderCode(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.PENDING)
                .user(user)
                .address(address)
                .build();
        Order createdOrder = orderRepository.save(order);

        // save all order items
        List<OrderItem> orderItem = orderRequest.orderItemRequests().stream()
                .map(orderItemRequest -> {
                            return OrderItem.builder()
                                    .price(orderItemRequest.price())
                                    .quantity(orderItemRequest.quantity())
                                    .totalPrice(orderItemRequest.totalPrice())
                                    .product(this.getProductById(orderItemRequest.productId()))
                                    .order(createdOrder)
                                    .build();
                        })
                .collect(Collectors.toList());

        List<OrderItem> createdListOfOrderItems = orderItemRepository.saveAll(orderItem);
        double total = 0;
        for (OrderItem orderItem1 : createdListOfOrderItems){
            total += orderItem1.getTotalPrice();
        }
        createdOrder.setOrderItems(createdListOfOrderItems);
        createdOrder.setTotal(total);


        return orderMapper.orderToOrderResponse(createdOrder);
    }

    @Override
    public OrderResponse update(OrderRequest orderRequest, Integer id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order doesn't exist"));
        orderRepository.delete(order);
        return true;
    }

    @Override
    public OrderResponse getByOrderCode(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new ResourceNotFoundException("order doesn't exist with this code : " + orderCode));

        return orderMapper.orderToOrderResponse(order);
    }

    @Override
    public Page<OrderResponse> getUserOrders(int pageNumber, int pageSize , String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));
        Page<Order> userOrders = orderRepository.findByUser(PageRequest.of(pageNumber , pageSize) , user);

        List<OrderResponse> orderResponseList = userOrders.getContent().stream()
                .map(order -> orderMapper.orderToOrderResponse(order))
                .collect(Collectors.toList());

        return new PageImpl<>(orderResponseList , userOrders.getPageable() , userOrders.getTotalElements());
    }
}
