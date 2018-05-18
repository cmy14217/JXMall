package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductSnapshotRepository snapshotRepository;

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody List<JsonNode> nodes, HttpServletResponse response){
        Orders order = orderRepository.saveAndFlush(new Orders(Float.valueOf(0), 1));
        Float totalPrice = Float.valueOf(0);
        for (JsonNode node : nodes) {
            Integer productId = node.get("productId").asInt();
            Integer purchaseCount = node.get("purchaseCount").asInt();

            Product product = productRepository.findById(productId).get();
            Inventory inventory = inventoryRepository.findById(productId).get();

            totalPrice += purchaseCount * product.getPrice();
            snapshotRepository.saveAndFlush(new ProductSnapshot(product.getName(), product.getDescription(), product.getPrice(), purchaseCount, order.getId()));
            inventoryRepository.updateLockedCount(inventory.getLockedCount() + purchaseCount, productId);
            //logisticsRecordRepository.saveAndFlush(new LogisticsRecord(order.getId()));
        }
        orderRepository.updateTotalPrice(totalPrice,order.getId());
        response.setHeader("location","http://IP:8083/orders/"+order.getId());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@RequestParam("orderStatus")String status,@PathVariable Integer id){
        if(status.equals("paid") || status.equals("withdrwan")) {
            orderRepository.updateOrderStatus(status,id);
            List<ProductSnapshot> snapshots = orderRepository.findById(id).get().getPurchaseItemList();
            for (ProductSnapshot snapshot : snapshots) {
                inventoryRepository.updateLockedCount(inventoryRepository.findById(snapshot.getId()).get()
                        .getLockedCount() - snapshot.getPurchaseCount(), snapshot.getId());
                if (status.equals("paid")) {
                    orderRepository.updatePaidTime(new Date(), id);
                    inventoryRepository.updateCount(inventoryRepository.findById(snapshot.getId()).get()
                            .getInventoryCount() - snapshot.getPurchaseCount(), snapshot.getId());
                } else if (status.equals("withdrawn")) {
                    orderRepository.updateWithdrawnTime(new Date(), id);
                }
            }
        } else if (status.equals("finish")) {
            orderRepository.updateOrderStatus(status,id);
            orderRepository.updateFinishTime(new Date(), id);
        } else{
            //
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Orders> findOrderById(@PathVariable Integer id){
        Optional<Orders> option = orderRepository.findById(id);
        if (!option.isPresent()) {
            //
        }
        return new ResponseEntity<>(option.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> findOrderByUserId(@RequestParam("userId")Integer userId){
        Optional<List<Orders>> option = orderRepository.findByUserId(userId);
        if (!option.isPresent()) {
            //
        }
        return new ResponseEntity<>(option.get(), HttpStatus.OK);
    }

}
