package com.sula.productmanagementsystem.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "product_comment")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_comment_id")
    private int productCommentId;
    @Column(length = 300)
    @Size(max = 300)
    private String comment;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public ProductComment() {
    }

    public ProductComment(int productCommentId, @Size(max = 300) String comment, Date createdAt, Product product) {
        this.productCommentId = productCommentId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.product = product;
    }

    public int getProductCommentId() {
        return productCommentId;
    }

    public void setProductCommentId(int productCommentId) {
        this.productCommentId = productCommentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "productCommentId=" + productCommentId +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", product=" + product +
                '}';
    }
}
