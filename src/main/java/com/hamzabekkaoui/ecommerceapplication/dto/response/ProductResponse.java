package com.hamzabekkaoui.ecommerceapplication.dto.response;

import lombok.*;



@Builder
public record ProductResponse(
         Integer productId,

         String name,

         String description,

         double price,

         Integer quantity,

         String categoryName
) {


}
