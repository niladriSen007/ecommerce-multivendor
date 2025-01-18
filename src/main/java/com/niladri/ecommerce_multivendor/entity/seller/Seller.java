package com.niladri.ecommerce_multivendor.entity.seller;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.niladri.ecommerce_multivendor.entity.address.Address;
import com.niladri.ecommerce_multivendor.dto.bankDetails.BankDetails;
import com.niladri.ecommerce_multivendor.dto.businessDetails.BusinessDetails;
import com.niladri.ecommerce_multivendor.enums.AccountStatus;
import com.niladri.ecommerce_multivendor.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress=new Address();

    private String GSTIN;

    private UserRole role = UserRole.SELLER;

    private  boolean isEmailVerified=false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;
}
