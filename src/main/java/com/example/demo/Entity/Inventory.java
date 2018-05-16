package com.example.demo.Entity;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer count;
    private Integer lockedCount;

    @OneToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private Product product;

    public Inventory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLockedCount() {
        return lockedCount;
    }

    public void setLockedCount(Integer lockedCount) {
        this.lockedCount = lockedCount;
    }
}
