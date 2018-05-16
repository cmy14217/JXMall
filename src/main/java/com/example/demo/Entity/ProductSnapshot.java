package com.example.demo.Entity;

import javax.persistence.*;

@Entity
public class ProductSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String productDescription;
    private Float purchasePrice;
    private Integer purchaseCount;
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "orderId",insertable = false,updatable = false)
    private Orders order;

    public ProductSnapshot() {
    }

    public ProductSnapshot(String productName, String productDescription, Float purchasePrice, Integer purchaseCount, Integer orderId) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.purchasePrice = purchasePrice;
        this.purchaseCount = purchaseCount;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
