package com.hamzabekkaoui.ecommerceapplication.dto.response;

import com.hamzabekkaoui.ecommerceapplication.dto.request.OrderRequest;
import lombok.*;

import java.util.List;


@Builder
public record OrderResponse(
         String userName,

         Integer orderId,

         String orderCode,

         String orderStatus,

         Double total,

         AddressResponse addressResponse,

        List<OrderItemResponse> orderItemResponses
) {


}
