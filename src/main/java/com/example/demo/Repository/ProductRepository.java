package com.example.demo.Repository;

import com.example.demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    //create product
    Product saveAndFlush(Product product);

    //update product
    @Modifying
    @Transactional
    @Query(value = "update Product set name=?1,description=?2,price=?3 where id=?4",nativeQuery = true)
    void updateProduct(String name,String description,Float price,Integer id);

    //find product by id
    Optional<Product> findById(Integer integer);

    //find product by name and description
    List<Product> findByNameContainingAndDescriptionContaining(String name,String description);

}
