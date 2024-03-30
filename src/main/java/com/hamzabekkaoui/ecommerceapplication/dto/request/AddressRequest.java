package com.hamzabekkaoui.ecommerceapplication.dto.request;

import lombok.*;


@Builder
public record AddressRequest(
        String country,
        String city,
        String street,
        String postalCode

) {

}
