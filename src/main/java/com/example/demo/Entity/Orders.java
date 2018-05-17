package com.example.demo.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float totalPrice;
    private String status;
    private Date createTime;
    private Date finishTime;
    private Date paidTime;
    private Date withdrawnTime;
    private Integer userId;

    @OneToMany(mappedBy = "order")
    private List<ProductSnapshot> products = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private LogisticsRecord logisticsRecord;

    @ManyToOne
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private Users user;

    public Orders() {
    }

    public Orders(Float totalPrice, Integer userId) {
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.status = "unpaid";
        this.createTime = new Date();
        this.finishTime = null;
        this.paidTime = null;
        this.withdrawnTime = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Date getWithdrawnTime() {
        return withdrawnTime;
    }

    public void setWithdrawnTime(Date withdrawnTime) {
        this.withdrawnTime = withdrawnTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ProductSnapshot> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSnapshot> products) {
        this.products = products;
    }
}
