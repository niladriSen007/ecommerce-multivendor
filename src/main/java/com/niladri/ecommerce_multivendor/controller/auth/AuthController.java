package com.niladri.ecommerce_multivendor.controller.auth;

import com.niladri.ecommerce_multivendor.dto.request.SignupRequest;
import com.niladri.ecommerce_multivendor.entity.user.User;
import com.niladri.ecommerce_multivendor.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {
        User user = authService.signup(signupRequest);
        return ResponseEntity.ok(user);
    }
}
