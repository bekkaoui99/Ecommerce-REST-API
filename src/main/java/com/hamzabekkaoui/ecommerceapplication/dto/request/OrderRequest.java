package com.hamzabekkaoui.ecommerceapplication.dto.request;

import com.hamzabekkaoui.ecommerceapplication.entity.Address;
import com.hamzabekkaoui.ecommerceapplication.entity.OrderItem;
import com.hamzabekkaoui.ecommerceapplication.entity.User;
import com.hamzabekkaoui.ecommerceapplication.enums.OrderStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {

    List<OrderItemRequest> orderItemRequests;

    private AddressRequest addressRequest;

    private String userName;

    private Double total;
}
