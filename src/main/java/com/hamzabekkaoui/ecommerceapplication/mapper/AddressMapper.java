package com.hamzabekkaoui.ecommerceapplication.mapper;

import com.hamzabekkaoui.ecommerceapplication.dto.request.AddressRequest;
import com.hamzabekkaoui.ecommerceapplication.dto.response.AddressResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

    public Address addressRequestToAddress(AddressRequest addressRequest){
        return  Address.builder()
                .country(addressRequest.country())
                .city(addressRequest.city())
                .street(addressRequest.street())
                .postalCode(addressRequest.postalCode())
                .build();
    }

    public AddressResponse addressToAddressResponse(Address address){
        return  AddressResponse.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .build();
    }
}
