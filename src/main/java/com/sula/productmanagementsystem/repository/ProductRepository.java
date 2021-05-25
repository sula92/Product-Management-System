package com.sula.productmanagementsystem.repository;

import com.sula.productmanagementsystem.model.Product;
import com.sula.productmanagementsystem.model.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findAllByStatusEqualsAndProductCategory_ProductCategoryIdEquals(@Param("status") Status status, @Param("categoryId") int productCategory);

    @Query("select p from Product p left join fetch p.productComments where p.status='A' and p.price>=500")
    List<Product> findAllPremiumProducts();
}
