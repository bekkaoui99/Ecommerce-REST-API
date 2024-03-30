package com.hamzabekkaoui.ecommerceapplication.security.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String userName,
        String accessToken,
        String refreshToken
) {
}
