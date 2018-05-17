package com.example.demo.Entity;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    private Integer id;
    private Integer inventoryCount;
    private Integer lockedCount;

    @OneToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private Product product;

    public Inventory() {

    }

    public Inventory(Integer id) {
        this.id = id;
        this.inventoryCount = 0;
        this.lockedCount = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(Integer inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public Integer getLockedCount() {
        return lockedCount;
    }

    public void setLockedCount(Integer lockedCount) {
        this.lockedCount = lockedCount;
    }

}
