package com.sula.productmanagementsystem.api;

import com.sula.productmanagementsystem.exception.ResourceNotFoundException;
import com.sula.productmanagementsystem.model.Product;
import com.sula.productmanagementsystem.model.ProductComment;
import com.sula.productmanagementsystem.model.enumeration.Status;
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

    @GetMapping("/products/{categoryId}")
    public List<Product> getAllProducts(@PathVariable int categoryId) throws ResourceNotFoundException {
        Status status=Status.A;
        try {
            return productRepository.findAllByStatusEqualsAndProductCategory_ProductCategoryIdEquals(status,categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("No Products Are Available For The Given Category");
        }
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() throws ResourceNotFoundException {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("No Product Are Available In The DB");
        }
    }

    @GetMapping("/products/premium")
    public List<Product> getAllPremiumProducts() throws ResourceNotFoundException {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("No Product Are Available In The DB");
        }
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable(value = "id") int productId, @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product=
                productRepository
                        .findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setLaunchDate(productDetails.getLaunchDate());
        product.setPrice(productDetails.getPrice());
        product.setProductCategory(product.getProductCategory());
        product.setProductComments(productDetails.getProductComments());
        product.setStatus(productDetails.getStatus());

        final Product updatedProduct=productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") int productId) throws Exception {
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
