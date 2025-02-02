package com.niladri.ecommerce_multivendor.utils;

import java.util.Random;

public class CreateOTP {
    public static String createOTP() {
        int otpLength = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
