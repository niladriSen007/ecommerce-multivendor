package com.niladri.ecommerce_multivendor.entity.cartItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.ecommerce_multivendor.entity.cart.Cart;
import com.niladri.ecommerce_multivendor.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Cart cart;
    @ManyToOne
    private Product product;
    private String size;
    private int quantity;
    private Integer mrpPrice;
    private Integer sellingPrice;
    private Long userId;
}
