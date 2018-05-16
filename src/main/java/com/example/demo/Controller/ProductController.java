package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productRepository.saveAndFlush(product);
    }

    @PutMapping(value = "/{id}")
    public void updateProduct(@RequestBody Product product,@PathVariable Integer id){
        productRepository.updateProduct(product.getName(),product.getDescription(),product.getPrice(),id);
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Integer id){
        Optional<Product> option = productRepository.findById(id);
        if(option.isPresent()){
            return option.get();
        }else {
            return null;
        }
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "name",required = false,defaultValue = "")String name,
                                     @RequestParam(value = "description",required = false,defaultValue = "")String description){
        return productRepository.findByNameContainingAndDescriptionContaining(name,description);
    }

}
