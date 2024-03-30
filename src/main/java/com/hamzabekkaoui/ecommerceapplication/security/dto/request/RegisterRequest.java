package com.hamzabekkaoui.ecommerceapplication.security.dto.request;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String userName,
        String mail,
        String password,
        String confirmationPassword
) {
}
