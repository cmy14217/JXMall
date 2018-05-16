package com.example.demo.Controller;

import com.example.demo.Entity.Inventory;
import com.example.demo.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/inventories")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @PutMapping(value = "/{id}")
    void updateCount(@RequestBody Integer addCount, @PathVariable Integer id){
        Optional<Inventory> option = inventoryRepository.findById(id);
        if(option.isPresent()){
            inventoryRepository.updateCount(option.get().getCount()+addCount, id);
        }
    }
}
