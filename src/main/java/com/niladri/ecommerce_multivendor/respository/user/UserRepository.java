package com.niladri.ecommerce_multivendor.respository.user;

import com.niladri.ecommerce_multivendor.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
