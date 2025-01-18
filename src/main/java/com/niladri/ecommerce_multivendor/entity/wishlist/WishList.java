package com.niladri.ecommerce_multivendor.entity.wishlist;

import com.niladri.ecommerce_multivendor.entity.product.Product;
import com.niladri.ecommerce_multivendor.entity.user.User;
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
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @ManyToMany
    private Set<Product> products = new HashSet<>();
}
