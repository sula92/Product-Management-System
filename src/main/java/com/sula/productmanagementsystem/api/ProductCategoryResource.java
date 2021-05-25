package com.sula.productmanagementsystem.api;

import com.sula.productmanagementsystem.exception.ResourceNotFoundException;
import com.sula.productmanagementsystem.model.Product;
import com.sula.productmanagementsystem.model.ProductCategory;
import com.sula.productmanagementsystem.model.enumeration.Status;
import com.sula.productmanagementsystem.repository.ProductCategoryRepository;
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
 * REST controller for managing ProductCategory.
 */
@RestController
@RequestMapping("/api/v1")
@Transactional
public class ProductCategoryResource {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    /**
     * Get all ProductCategory list.
     *
     * @return the list
     */
    @GetMapping("/category")
    public List<ProductCategory> getAllProductCategories(){
        return productCategoryRepository.findAll();
    }

    /**
     * Gets ProductCategories by ProductCategoryId.
     *
     * @param categoryId the categoryId
     * @return the ProductCategory by ProductCategoryId
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/category/{id}")
    public ProductCategory getAllProductCategory(@PathVariable(value = "id") int categoryId) throws ResourceNotFoundException {
        return productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Product not found on :: " + categoryId));
    }

    /**
     * Create ProductCategory productCategory.
     *
     * @param productCategory the ProductCategory
     * @return the productCategory
     */
    @PostMapping("/category")
    public ProductCategory saveProductCategory(@Valid @RequestBody ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    /**
     * Update ProductCategory response entity.
     *
     * @param categoryId the ProductCategoryId
     * @param productCategoryDatails the ProductCategory details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/category/{id}")
    public ResponseEntity<ProductCategory> editProductCategory(@PathVariable(value = "id") int categoryId, @Valid @RequestBody ProductCategory productCategoryDatails) throws ResourceNotFoundException {
        ProductCategory productCategory=
                productCategoryRepository
                        .findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Category not found on :: " + categoryId));

        productCategory.setName(productCategoryDatails.getName());
        productCategory.setDescription(productCategoryDatails.getDescription());

        final ProductCategory updatedProductCategory=productCategoryRepository.save(productCategory);
        return ResponseEntity.ok(updatedProductCategory);
    }

    /**
     * Delete ProductCategory map.
     *
     * @param productCategoryId the productCategoryId
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/category/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") int productCategoryId) throws Exception {
        ProductCategory productCategory=
                productCategoryRepository
                        .findById(productCategoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productCategoryId));

        productCategoryRepository.delete(productCategory);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", Boolean.TRUE);
        return response;
    }
}
