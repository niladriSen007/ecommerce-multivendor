package com.niladri.ecommerce_multivendor.service.auth;

import com.niladri.ecommerce_multivendor.dto.request.LoginRequest;
import com.niladri.ecommerce_multivendor.dto.request.SignupRequest;
import com.niladri.ecommerce_multivendor.entity.user.User;
import com.niladri.ecommerce_multivendor.entity.verificationCode.VerificationCode;
import com.niladri.ecommerce_multivendor.enums.UserRole;
import com.niladri.ecommerce_multivendor.exception.UserAlreadyExistsException;
import com.niladri.ecommerce_multivendor.respository.user.UserRepository;
import com.niladri.ecommerce_multivendor.service.cart.CartService;
import com.niladri.ecommerce_multivendor.service.provider.JwtProvider;
import com.niladri.ecommerce_multivendor.service.user.UserService;
import com.niladri.ecommerce_multivendor.service.verificationCode.VerificationCodeService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements AuthServiceInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final JwtProvider jwtProvider;
    private final VerificationCodeService verificationCodeService;
    private final UserService userService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, CartService cartService,
                       JwtProvider jwtProvider, VerificationCodeService verificationCodeService, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
        this.jwtProvider = jwtProvider;
        this.verificationCodeService = verificationCodeService;
        this.userService = userService;
    }

    @Override
    public String createUser(SignupRequest signupRequest) {

        VerificationCode verificationCodeUser = verificationCodeService.findUserByEmail(signupRequest.getEmail());

        if (verificationCodeUser == null || !verificationCodeUser.getOtp().equals(signupRequest.getOtp())) {
            throw new RuntimeException("Verification code mismatch");
        }
        User user = userRepository.findByEmail(signupRequest.getEmail());
        if (user != null) {
            throw new UserAlreadyExistsException("User with email " + signupRequest.getEmail() + " already exists");
        }
        user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFullName(signupRequest.getFullName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User user1 = userRepository.save(user);
        // Creating a cart for the user
        cartService.createCart(user1);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.CUSTOMER.toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateJwtToken(authentication);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getOtp());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateJwtToken(authentication);
    }

    private Authentication authenticate(String email, String password, String otp) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println(passwordEncoder.encode(password));
            System.out.println(user.getPassword());
            throw new BadCredentialsException("Invalid Password");
        }
        VerificationCode verificationCodeUser = verificationCodeService.findUserByEmail(email);
        if (verificationCodeUser == null || !verificationCodeUser.getOtp().equals(otp)) {
            throw new RuntimeException("Verification code mismatch");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), authorities);
    }

    public void sendOTP(String email) {
        String SIGNING_PREFIX = "signing_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());
            userService.getUserByEmail(email);
        }

        verificationCodeService.createVerificationCode(email);
    }
}
