package com.niladri.ecommerce_multivendor.controller.auth;

import com.niladri.ecommerce_multivendor.dto.authResponse.AuthResponse;
import com.niladri.ecommerce_multivendor.dto.request.SendOtpRequest;
import com.niladri.ecommerce_multivendor.dto.request.SignupRequest;
import com.niladri.ecommerce_multivendor.service.auth.AuthService;
import com.niladri.ecommerce_multivendor.service.verificationCode.VerificationCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final VerificationCodeService verificationCodeService;


    public AuthController(AuthService authService, VerificationCodeService verificationCodeService) {
        this.authService = authService;
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody SignupRequest signupRequest) {
        String token = authService.createUser(signupRequest);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMessage("User created successfully");
        return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/sendotp")
    public ResponseEntity<AuthResponse> sendOtp(@RequestBody SendOtpRequest signupRequest) {
        String token = verificationCodeService.createVerificationCode(signupRequest.getEmail());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMessage("OTP sent successfully");
        return ResponseEntity.ok(authResponse);
    }
}
