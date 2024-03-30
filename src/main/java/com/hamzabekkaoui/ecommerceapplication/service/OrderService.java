package com.hamzabekkaoui.ecommerceapplication.service;

import com.hamzabekkaoui.ecommerceapplication.dto.request.OrderRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.OrderResponse;
import org.springframework.data.domain.Page;

public interface OrderService extends CrudService<OrderRequest , OrderResponse , Integer>{
    OrderResponse getByOrderCode(String orderCode);

    Page<OrderResponse> getUserOrders(int pageNumber, int pageSize ,String userName);
}
