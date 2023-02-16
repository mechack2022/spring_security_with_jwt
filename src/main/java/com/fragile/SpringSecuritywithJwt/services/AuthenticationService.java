package com.fragile.SpringSecuritywithJwt.services;

import com.fragile.SpringSecuritywithJwt.entities.User;
import com.fragile.SpringSecuritywithJwt.enums.Role;
import com.fragile.SpringSecuritywithJwt.payloads.AuthenticationRequest;
import com.fragile.SpringSecuritywithJwt.payloads.AuthenticationResponse;
import com.fragile.SpringSecuritywithJwt.payloads.RegisterRequest;
import com.fragile.SpringSecuritywithJwt.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//public class AuthenticationService {
//    private PasswordEncoder passwordEncoder;
//
//    private JwtService jwtService;
//
//    private UserRepo userRepo;
//
//    private AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse registerUser(RegisterRequest request) {
//        var user = User.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .about(request.getAbout())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//        userRepo.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//
//    }
//
//    public AuthenticationResponse authenticateUser(RegisterRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword())
//        );
//        var user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }
//}
    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {
        private final UserRepo repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
            var user = User.builder()
                    .name(request.getName())
                    .about(request.getAbout())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }


