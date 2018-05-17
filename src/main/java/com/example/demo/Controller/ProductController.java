package com.example.demo.Controller;

import com.example.demo.Entity.Inventory;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.InventoryRepository;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        Product addProduct = productRepository.saveAndFlush(product);
        inventoryRepository.saveAndFlush(new Inventory(addProduct.getId()));
        return new ResponseEntity<>(addProduct,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody Product product,@PathVariable Integer id){
        Optional<Product> option = productRepository.findById(id);
        if(option.isPresent()){
            productRepository.updateProduct(product.getName(),product.getDescription(),product.getPrice(),id);
        }else {
            //throw new ProductException("该id不对应任何商品，修改无效");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        Optional<Product> option = productRepository.findById(id);
        if(!option.isPresent()){
            //throw exception
        }
        return new ResponseEntity<>(option.get(),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "name",required = false,defaultValue = "")String name,
                                     @RequestParam(value = "description",required = false,defaultValue = "")String description){
        List<Product> products = productRepository.findByNameContainingAndDescriptionContaining(name,description);
        if(products.size() == 0){
            //throw exception
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
