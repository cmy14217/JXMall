package com.example.demo.Repository;

import com.example.demo.Entity.ProductSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSnapshotRepository extends JpaRepository<ProductSnapshot,Integer> {
    ProductSnapshot saveAndFlush(ProductSnapshot snapshot);
}
