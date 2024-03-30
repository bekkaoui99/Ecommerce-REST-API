package com.hamzabekkaoui.ecommerceapplication.mapper;

import com.hamzabekkaoui.ecommerceapplication.dto.request.OrderRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.AddressResponse;
import com.hamzabekkaoui.ecommerceapplication.dto.response.OrderItemResponse;
import com.hamzabekkaoui.ecommerceapplication.dto.response.OrderResponse;
import com.hamzabekkaoui.ecommerceapplication.dto.response.ProductResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;
    private final AddressMapper addressMapper;


    public OrderResponse orderToOrderResponse(Order order){

        List<OrderItemResponse> orderItemResponse = order.getOrderItems()
                .stream()
                .map(orderItem -> orderItemMapper.orderItemToOrderItemResponse(orderItem))
                .collect(Collectors.toList());


        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderCode(order.getOrderCode())
                .userName(order.getUser().getUserName())
                .orderStatus(order.getOrderStatus().toString())
                .addressResponse(addressMapper.addressToAddressResponse(order.getAddress()))
                .orderItemResponses(orderItemResponse)
                .total(order.getTotal())
                .build();
    }

}
