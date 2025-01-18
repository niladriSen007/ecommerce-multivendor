package com.niladri.ecommerce_multivendor.entity.transaction;

import com.niladri.ecommerce_multivendor.entity.order.Order;
import com.niladri.ecommerce_multivendor.entity.seller.Seller;
import com.niladri.ecommerce_multivendor.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @OneToOne
    private Order order;

    @ManyToOne
    private Seller seller;

    private LocalDateTime date= LocalDateTime.now();
}
