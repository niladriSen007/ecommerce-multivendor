package com.niladri.ecommerce_multivendor.entity.paymentOrder;

import com.niladri.ecommerce_multivendor.entity.order.Order;
import com.niladri.ecommerce_multivendor.entity.user.User;
import com.niladri.ecommerce_multivendor.enums.PaymentMethod;
import com.niladri.ecommerce_multivendor.enums.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;
    private PaymentMethod paymentMethod;
    private String paymentLinkId;
    @ManyToOne
    private User user;
    @OneToMany
    private Set<Order> orders = new HashSet<>();
}
