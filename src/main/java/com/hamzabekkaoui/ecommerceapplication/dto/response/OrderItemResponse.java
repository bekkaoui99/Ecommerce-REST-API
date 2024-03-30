package com.hamzabekkaoui.ecommerceapplication.dto.response;


import lombok.*;


@Builder
public record OrderItemResponse(
         Integer OrderItemId,

         Integer OrderId,

         ProductResponse productResponse,

         Integer quantity,

         Double price,

         Double totalPrice
) {


}
