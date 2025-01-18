package com.niladri.ecommerce_multivendor.service.auth;

import com.niladri.ecommerce_multivendor.dto.request.SignupRequest;
import com.niladri.ecommerce_multivendor.entity.user.User;

public interface AuthServiceInterface {
    User signup(SignupRequest signupRequest);
}
