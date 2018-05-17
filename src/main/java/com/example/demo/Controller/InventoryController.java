package com.example.demo.Controller;

import com.example.demo.Entity.Inventory;
import com.example.demo.Repository.InventoryRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/inventories")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCount(@RequestBody JsonNode node, @PathVariable Integer id){
        Optional<Inventory> option = inventoryRepository.findById(id);
        if(option.isPresent()){
            Integer addCount = node.get("count").asInt();
            inventoryRepository.updateCount(option.get().getInventoryCount()+addCount, id);
        }
    }
}
