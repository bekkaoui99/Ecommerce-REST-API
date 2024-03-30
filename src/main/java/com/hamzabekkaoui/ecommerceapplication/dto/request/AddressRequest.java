package com.hamzabekkaoui.ecommerceapplication.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {

    private String country;

    private String city;

    private String street;

    private String postalCode;

}
