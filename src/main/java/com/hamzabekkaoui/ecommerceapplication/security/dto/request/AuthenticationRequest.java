package com.hamzabekkaoui.ecommerceapplication.security.dto.request;

public record AuthenticationRequest(
        String mail,
        String password
) {
}
