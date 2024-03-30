package com.hamzabekkaoui.ecommerceapplication.dto.response;

import lombok.*;


@Builder
public record CategoryResponse(
         Integer categoryId,

         String categoryName,

         String description
) {


}
