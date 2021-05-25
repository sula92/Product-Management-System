package com.sula.productmanagementsystem.api;

import com.sula.productmanagementsystem.exception.ResourceAlreadyExisistException;
import com.sula.productmanagementsystem.exception.ResourceNotFoundException;
import com.sula.productmanagementsystem.model.Product;
import com.sula.productmanagementsystem.model.enumeration.Status;
import com.sula.productmanagementsystem.model.projection.PremiumProduct;
import com.sula.productmanagementsystem.repository.ProductCategoryRepository;
import com.sula.productmanagementsystem.repository.ProductCommentRepository;
import com.sula.productmanagementsystem.repository.ProductRepository;
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
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api/v1")
@Transactional
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCommentRepository productCommentRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    /**
     * Gets users by categoryId.
     *
     * @param categoryId the category id
     * @return the products by categoryId
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/products/{categoryId}")
    public List<Product> getAllProducts(@PathVariable int categoryId) throws ResourceNotFoundException {
        Status status=Status.A;
        try {
            return productRepository.findAllByStatusEqualsAndProductCategory_ProductCategoryIdEquals(status,categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No Products Are Available For The Given Category");
        }
    }

    /**
     * Get all products list.
     *
     * @return the list
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() throws ResourceNotFoundException {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "We were unable to find the specified resource.");
        }
    }

    @GetMapping("/products/premium")
    public List<PremiumProduct> getAllPremiumProducts() throws ResourceNotFoundException {

        try {
            return productRepository.findAllPremiumProducts();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "We were unable to find the specified resource.");
        }

    }

    /**
     * Create product.
     *
     * @param product the product
     * @return the product
     */
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@Valid @RequestBody Product product){
        Product product1=
                productRepository
                        .findById(product.getProductId())
                        .orElseThrow(() -> new ResourceAlreadyExisistException(HttpStatus.NOT_ACCEPTABLE,"Product is already exists on :: " + product.getProductId()));

        return productRepository.save(product);
    }

    /**
     * Update product response entity.
     *
     * @param productId the product id
     * @param productDetails the product details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable(value = "id") int productId, @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product=
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Product not found on :: " + productId));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setLaunchDate(productDetails.getLaunchDate());
        product.setPrice(productDetails.getPrice());
        product.setProductCategory(product.getProductCategory());
        product.setStatus(productDetails.getStatus());

        final Product updatedProduct=productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Soft Delete product map.
     *
     * @param productId the product id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> softDeleteProduct(@PathVariable(value = "id") int productId) throws Exception {
        Product product=
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));

        product.setStatus(Status.D);
        productRepository.save(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", Boolean.TRUE);
        return response;
    }
}
