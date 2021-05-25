package com.sula.productmanagementsystem.repository;

import com.sula.productmanagementsystem.model.Product;
import com.sula.productmanagementsystem.model.enumeration.Status;
import com.sula.productmanagementsystem.model.projection.PremiumProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Product entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findAllByStatusEqualsAndProductCategory_ProductCategoryIdEquals(@Param("status") Status status, @Param("categoryId") int productCategory);

    @Query(value = "SELECT p.name, p.description, p.price, pcat.name as productCategory, pcom.comment as comment FROM product p LEFT JOIN product_category pcat ON p.product_category_id=pcat.product_category_id LEFT JOIN product_comment pcom ON p.product_id=pcom.product_id WHERE p.price>=500.00", nativeQuery = true)
    List<PremiumProduct> findAllPremiumProducts();

}
