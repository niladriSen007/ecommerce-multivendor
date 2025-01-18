package com.niladri.ecommerce_multivendor.dto.home;

import com.niladri.ecommerce_multivendor.entity.deal.Deal;
import com.niladri.ecommerce_multivendor.entity.homeCategory.HomeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Home {
    private List<HomeCategory> grid;

    private List<HomeCategory> shopByCategories;

    private List<HomeCategory> electricCategories;

    private List<HomeCategory> dealCategories;

    private List<Deal> deals;
}
