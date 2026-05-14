package com.taskManagement.auth;

import com.taskManagement.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        // Static Username & Password
        if ("admin".equals(request.getUsername())
                &&
                "admin123".equals(request.getPassword())) {

            String token =
                    jwtUtil.generateToken(
                            request.getUsername());

            return ResponseEntity.ok(
                    new LoginResponse(token));
        }

        return ResponseEntity.badRequest()
                .body("Invalid Credentials");
    }
}