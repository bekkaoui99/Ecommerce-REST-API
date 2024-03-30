package com.hamzabekkaoui.ecommerceapplication.security.Controller;

import com.hamzabekkaoui.ecommerceapplication.security.dto.request.AuthenticationRequest;
import com.hamzabekkaoui.ecommerceapplication.security.dto.request.RegisterRequest;
import com.hamzabekkaoui.ecommerceapplication.security.dto.response.AuthenticationResponse;
import com.hamzabekkaoui.ecommerceapplication.security.dto.response.RegisterResponse;
import com.hamzabekkaoui.ecommerceapplication.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest){

        RegisterResponse response = authenticationService.register(registerRequest);
        return new ResponseEntity<>(response , HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest){

        AuthenticationResponse response = authenticationService.login(authenticationRequest);
        return new ResponseEntity<>(response , HttpStatus.CREATED);

    }

    @PostMapping("/refreshToken")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        authenticationService.refreshToken(request, response);
    }

}
