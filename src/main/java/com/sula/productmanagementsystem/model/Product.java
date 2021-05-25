package com.sula.productmanagementsystem.model;

import com.sula.productmanagementsystem.model.enumeration.Status;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

/**
 * Represents product details.\n@author sula
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String name;
    @Size(max = 500)
    private String description;
    @Column(name = "price", columnDefinition = "Decimal(10,2)")
    private double price;
    @ManyToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "product_category_id")
    private ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    @Size(max = 1)
    @Column(length = 1)
    private Status status;
    @Column(name = "launched_date")
    private Date launchDate;
   /* @OneToMany(mappedBy = "product")
    private List<ProductComment> productComments;*/

    public Product() {
    }

    public Product(int productId, @Size(max = 100) String name, @Size(max = 500) String description, double price, ProductCategory productCategory, @Size(max = 1) Status status, Date launchDate) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
        this.status = status;
        this.launchDate = launchDate;
    }

    public Product(int productId, @Size(max = 100) String name, @Size(max = 500) String description, double price, ProductCategory productCategory, @Size(max = 1) Status status, Date launchDate, List<ProductComment> productComments) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
        this.status = status;
        this.launchDate = launchDate;
        //this.productComments = productComments;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

   /* public List<ProductComment> getProductComments() {
        return productComments;
    }*/

   /* public void setProductComments(List<ProductComment> productComments) {
        this.productComments = productComments;
    }*/

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productCategory=" + productCategory +
                ", status=" + status +
                ", launchDate=" + launchDate +
                '}';
    }
}
