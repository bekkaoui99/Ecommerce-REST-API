package com.hamzabekkaoui.ecommerceapplication.dto.response;


import lombok.*;


@Builder
public record AddressResponse(
        String country,
        String city,
        String street,
        String postalCode
) {


}
