package com.sula.productmanagementsystem.repository;

import com.sula.productmanagementsystem.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProductCategory entity.
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
