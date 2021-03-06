package com.example.demo.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LogisticsRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logisticsStatus;
    private Date outboundTime;
    private Date signedTime;
    private Date deliveryTime;
    private String deliveryMan;
    private Integer orderId;

    @OneToOne
    @JoinColumn(name = "orderId",insertable = false,updatable = false)
    private Orders order;

    public LogisticsRecord() {
    }

    public LogisticsRecord(Integer orderId) {
        this.orderId = orderId;
        this.logisticsStatus = "readyToShip";
        this.outboundTime = new Date();
        this.deliveryTime = null;
        this.signedTime = null;
        this.deliveryMan = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Date getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
    }

    public Date getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(Date signedTime) {
        this.signedTime = signedTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
}
