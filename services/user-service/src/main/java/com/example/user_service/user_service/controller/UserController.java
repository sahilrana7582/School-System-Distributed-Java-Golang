package com.example.user_service.user_service.controller;

import com.example.user_service.user_service.dto.AuthRequest;
import com.example.user_service.user_service.dto.AuthResponse;
import com.example.user_service.user_service.entity.User;
import com.example.user_service.user_service.jwt.Claim;
import com.example.user_service.user_service.jwt.JwtService;
import com.example.user_service.user_service.repository.UserRepository;
import com.example.user_service.user_service.service.DaoUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final DaoUserService daoUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserController(
            AuthenticationManager authenticationManager,
            DaoUserService daoUserService,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.daoUserService = daoUserService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@NotNull @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        daoUserService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@NotNull @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var userDetails =  (User) authentication.getPrincipal();

        String token = jwtService.generateToken(new Claim(
                userDetails.getName(),
                userDetails.getTenantId().toString(),
                userDetails.getId().toString(),
                userDetails.getSchoolId().toString()
        ));

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
