package com.niladri.ecommerce_multivendor.entity.verificationCode;

import com.niladri.ecommerce_multivendor.entity.seller.Seller;
import com.niladri.ecommerce_multivendor.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String otp;

    private String email;

    @OneToOne
    private User user;

    @OneToOne
    private Seller seller;
}
