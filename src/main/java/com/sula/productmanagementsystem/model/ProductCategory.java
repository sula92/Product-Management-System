package com.sula.productmanagementsystem.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private int productCategoryId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 500)
    @Size(max = 500)
    private String description;
    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

    public ProductCategory() {
    }

    public ProductCategory(int productCategoryId, String name, @Size(max = 500) String description) {
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.description = description;
    }

    public ProductCategory(int productCategoryId, String name, @Size(max = 500) String description, List<Product> products) {
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "productCategoryId=" + productCategoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
