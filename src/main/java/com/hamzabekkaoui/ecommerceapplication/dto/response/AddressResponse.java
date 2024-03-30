package com.hamzabekkaoui.ecommerceapplication.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressResponse {

    private String country;

    private String city;

    private String street;

    private String postalCode;

}
