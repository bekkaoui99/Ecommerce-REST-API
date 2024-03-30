package com.hamzabekkaoui.ecommerceapplication.controller;

import com.hamzabekkaoui.ecommerceapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    @GetMapping
    public String getAllUsers(){
        return "all users";
    }

    @GetMapping("/profile")
    public Authentication getProfile(Authentication manager){
        return manager;
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String getCustomer(){
        return "customer";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdmin() {
        return "admin";
    }


}
