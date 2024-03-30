package com.hamzabekkaoui.ecommerceapplication.dto.response;

import com.hamzabekkaoui.ecommerceapplication.dto.request.OrderRequest;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {

    private String userName;

    private Integer orderId;

    private String orderCode;

    private String orderStatus;

    private Double total;

    private AddressResponse addressResponse;

    List<OrderItemResponse> orderItemResponses;
}
