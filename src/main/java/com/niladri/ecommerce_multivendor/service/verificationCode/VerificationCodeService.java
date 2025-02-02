package com.niladri.ecommerce_multivendor.service.verificationCode;

import com.niladri.ecommerce_multivendor.entity.verificationCode.VerificationCode;
import com.niladri.ecommerce_multivendor.respository.verification.VerificationRepository;
import com.niladri.ecommerce_multivendor.service.email.EmailService;
import com.niladri.ecommerce_multivendor.utils.CreateOTP;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    private final VerificationRepository verificationRepository;
    private final EmailService emailService;

    public VerificationCodeService(VerificationRepository verificationRepository, EmailService emailService) {
        this.verificationRepository = verificationRepository;
        this.emailService = emailService;
    }

    public VerificationCode findUserByEmail(String email) {
        return verificationRepository.findByEmail(email);
    }

    public String createVerificationCode(String email) {
        VerificationCode user = verificationRepository.findByEmail(email);
        if (user != null) {
            verificationRepository.delete(user);
        }

        String newOtp = CreateOTP.createOTP();

        VerificationCode newUser = new VerificationCode();
        newUser.setEmail(email);
        newUser.setOtp(newOtp);
        verificationRepository.save(newUser);
        emailService.sendVerificainMail(email, newOtp, "OTP for Email verification", "Your OTP is: ");
        return user.getOtp();
    }
}
