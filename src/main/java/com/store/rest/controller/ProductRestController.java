package com.store.rest.controller;

import com.store.entity.Product;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(productService.GetOneProduct(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 không tìm thấy
        }
    }

}
