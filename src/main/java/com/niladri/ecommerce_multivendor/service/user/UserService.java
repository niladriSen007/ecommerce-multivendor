package com.niladri.ecommerce_multivendor.service.user;

import com.niladri.ecommerce_multivendor.entity.seller.Seller;
import com.niladri.ecommerce_multivendor.entity.user.User;
import com.niladri.ecommerce_multivendor.enums.UserRole;
import com.niladri.ecommerce_multivendor.respository.seller.SellerRepository;
import com.niladri.ecommerce_multivendor.respository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final String SELLER_PREFIX = "seller_";
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public UserService(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith(SELLER_PREFIX)) {
            String actualUserName = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUserName);
            if (seller != null) {
                return buildUserForAuthentication(seller.getEmail(), seller.getPassword(), UserRole.SELLER);
            }
        } else {
            User user = userRepository.findByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with email " + username + " not found");
            }
            return buildUserForAuthentication(user.getEmail(), user.getPassword(), user.getRole());
        }
        throw new UsernameNotFoundException("User with email " + username + " not found");
    }

    private UserDetails buildUserForAuthentication(String email, String password, UserRole role) {
        if (role == null) role = UserRole.CUSTOMER;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
        return new org.springframework.security.core.userdetails.User(email, password, authorities);

    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
        return user;
    }
}
