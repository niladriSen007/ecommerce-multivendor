package com.niladri.ecommerce_multivendor.service.auth;

import com.niladri.ecommerce_multivendor.dto.request.LoginRequest;
import com.niladri.ecommerce_multivendor.dto.request.SignupRequest;
import com.niladri.ecommerce_multivendor.entity.user.User;

public interface AuthServiceInterface {
    String createUser(SignupRequest signupRequest);

    String login(LoginRequest loginRequest);
}
