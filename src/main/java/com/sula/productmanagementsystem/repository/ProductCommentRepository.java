package com.sula.productmanagementsystem.repository;

import com.sula.productmanagementsystem.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {
}
