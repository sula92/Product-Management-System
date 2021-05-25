package com.sula.productmanagementsystem.api;

import com.sula.productmanagementsystem.exception.ResourceNotFoundException;
import com.sula.productmanagementsystem.model.ProductComment;
import com.sula.productmanagementsystem.repository.ProductCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for managing ProductComment.
 */
@RestController
@RequestMapping("/api/v1")
@Transactional
public class ProductCommentResource {

    @Autowired
    ProductCommentRepository ProductCommentRepository;

    /**
     * Get all ProductComment list.
     *
     * @return the list
     */
    @GetMapping("/comments")
    public List<ProductComment> getAllProductCategories(){
        return ProductCommentRepository.findAll();
    }

    /**
     * Gets ProductComment by ProductCommentId.
     *
     * @param commentsId the ProductComment id
     * @return the ProductComment by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/comments/{id}")
    public ProductComment getAllProductComment(@PathVariable(value = "id") int commentsId) throws ResourceNotFoundException {
        return ProductCommentRepository.findById(commentsId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Product not found on :: " + commentsId));
    }

    /**
     * Create user user.
     *
     * @param ProductComment the ProductComment
     * @return the ProductComment
     */
    @PostMapping("/comments")
    public ProductComment saveProductComment(@Valid @RequestBody ProductComment ProductComment){
        return ProductCommentRepository.save(ProductComment);
    }

    /**
     * Update user response entity.
     *
     * @param commentsId the ProductCommentId
     * @param productCommentDatails the user details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<ProductComment> editProductComment(@PathVariable(value = "id") int commentsId, @Valid @RequestBody ProductComment productCommentDatails) throws ResourceNotFoundException {
        ProductComment productComment=
                ProductCommentRepository
                        .findById(commentsId)
                        .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"comments not found on :: " + commentsId));

        productComment.setComment(productCommentDatails.getComment());
        productComment.setCreatedAt(productCommentDatails.getCreatedAt());
        productComment.setProduct(productCommentDatails.getProduct());

        final ProductComment updatedProductComment=ProductCommentRepository.save(productComment);
        return ResponseEntity.ok(updatedProductComment);
    }

    /**
     * Delete user map.
     *
     * @param productCommentId the productCommentId
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/comments/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") int productCommentId) throws Exception {
        ProductComment productComment=
                ProductCommentRepository
                        .findById(productCommentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productCommentId));

        ProductCommentRepository.delete(productComment);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", Boolean.TRUE);
        return response;
    }
}
