package com.hamzabekkaoui.ecommerceapplication.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamzabekkaoui.ecommerceapplication.security.dto.request.AuthenticationRequest;
import com.hamzabekkaoui.ecommerceapplication.security.dto.request.RegisterRequest;
import com.hamzabekkaoui.ecommerceapplication.security.dto.response.AuthenticationResponse;
import com.hamzabekkaoui.ecommerceapplication.security.dto.response.RegisterResponse;
import com.hamzabekkaoui.ecommerceapplication.entity.User;
import com.hamzabekkaoui.ecommerceapplication.enums.Role;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceAlreadyExist;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceNotFoundException;
import com.hamzabekkaoui.ecommerceapplication.repository.UserRepository;
import com.hamzabekkaoui.ecommerceapplication.security.model.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest registerRequest) {
        boolean exist = userRepository.existsUserByMail(registerRequest.mail());
        if(exist) throw new ResourceAlreadyExist("this mail : " + registerRequest.mail() + " already exist ");
        if(!registerRequest.password().equals(registerRequest.confirmationPassword()))
            throw new IllegalArgumentException("confirmation password doesn't match with the password :(");


        Set<Role> authorities = new HashSet<>();
        authorities.add(Role.CUSTOMER);

        User createdUser = userRepository.save(User.builder()
                .userId(UUID.randomUUID().toString())
                .userName(registerRequest.userName())
                .mail(registerRequest.mail())
                .password(passwordEncoder.encode(registerRequest.password()))
                .authorities(authorities)
                .build());

        return RegisterResponse.builder()
                .id(createdUser.getUserId())
                .mail(createdUser.getMail())
                .userName(createdUser.getUserName())
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        SecurityUser user = userRepository.findByMail(authenticationRequest.mail())
                .map(SecurityUser::new)
                .orElseThrow(() -> new ResourceNotFoundException("something went wrong"));

        if(user.getPassword().equals(passwordEncoder.encode(authenticationRequest.password())))
            throw new IllegalArgumentException("something went wrong");

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.mail(), authenticationRequest.password())
        );

        Map<String, Object> claims = new HashMap<>();
        String roles = authenticate.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        claims.put("role", roles);
        String accessToken = jwtService.generateToken(claims, user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .userName(user.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }



    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response
    ) throws Exception {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);

        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.userRepository.findByMail(username)
                    .map(SecurityUser::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found :( "));
            if (jwtService.isTokenValid(refreshToken, user)) {
                String newAccessToken = jwtService.generateToken(new HashMap<>(), user);


                var _response = AuthenticationResponse.builder()
                        .userName(user.getUsername())
                        .accessToken(newAccessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper()
                        .writeValue(
                                response.getOutputStream(),
                                _response);
            }
        }
    }
}
