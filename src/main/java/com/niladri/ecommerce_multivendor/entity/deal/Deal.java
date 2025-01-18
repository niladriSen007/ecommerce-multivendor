package com.niladri.ecommerce_multivendor.entity.deal;

import com.niladri.ecommerce_multivendor.entity.homeCategory.HomeCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer discount;

    @OneToOne
    private HomeCategory category;
}
