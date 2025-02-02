package com.niladri.ecommerce_multivendor.respository.verification;

import com.niladri.ecommerce_multivendor.entity.verificationCode.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByEmail(String email);
}
