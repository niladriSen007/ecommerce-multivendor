package com.niladri.ecommerce_multivendor.entity.order;

import com.niladri.ecommerce_multivendor.dto.paymentDetails.PaymentDetails;
import com.niladri.ecommerce_multivendor.entity.address.Address;
import com.niladri.ecommerce_multivendor.entity.orderItem.OrderItem;
import com.niladri.ecommerce_multivendor.entity.user.User;
import com.niladri.ecommerce_multivendor.enums.OrderStatus;
import com.niladri.ecommerce_multivendor.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    @ManyToOne
    private User user;
    private Long sellerId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();
    @ManyToOne
    private Address shippingAddress;
    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();
    private double totalMrpPrice;
    private Integer totalSellingPrice;
    private Integer discount;
    private OrderStatus orderStatus;
    private int totalItem;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusDays(7);
}
