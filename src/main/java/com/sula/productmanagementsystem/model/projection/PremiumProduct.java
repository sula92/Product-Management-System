package com.sula.productmanagementsystem.model.projection;

/**
 * ProjectionDTO for get products which have a price of greater than 500 as premium products.
 */
public interface PremiumProduct {
    String getName();

    String getDescription();

    double getPrice();

    String getProductCategory();

    String getComment();
}
