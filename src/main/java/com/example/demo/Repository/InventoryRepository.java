package com.example.demo.Repository;

import com.example.demo.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    //create inventory
    Inventory saveAndFlush(Inventory inventory);

    //get Invertory by id
    Optional<Inventory> findById(Integer id);

    //alter the num of count
    @Modifying
    @Transactional
    @Query(value = "update Inventory set inventoryCount = ?1 where id=?2",nativeQuery = true)
    void updateCount(Integer count,Integer id);

    //alter the lockedCount
    @Modifying
    @Transactional
    @Query(value = "update Inventory set lockedCount=?1 where id=?2",nativeQuery = true)
    void updateLockedCount(Integer lockedCount,Integer id);
}
