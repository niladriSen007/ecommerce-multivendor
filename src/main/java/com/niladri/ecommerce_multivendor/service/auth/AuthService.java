package com.niladri.ecommerce_multivendor.service.auth;

import com.niladri.ecommerce_multivendor.dto.request.SignupRequest;
import com.niladri.ecommerce_multivendor.entity.user.User;
import com.niladri.ecommerce_multivendor.respository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceInterface {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signup(SignupRequest signupRequest) {
        User user = new User();
        user.setFullName("Niladri");
        user.setEmail("");
        return userRepository.save(user);
    }
}
