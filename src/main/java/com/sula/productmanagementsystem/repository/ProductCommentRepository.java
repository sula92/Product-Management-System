package com.sula.productmanagementsystem.repository;

import com.sula.productmanagementsystem.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProductComment entity.
 */
@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {
}
