package com.demo.productquerymicroservice.controller;

import com.demo.productquerymicroservice.entity.Product;
import com.demo.productquerymicroservice.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    @Autowired
    private ProductQueryService productQueryService;

    @GetMapping
    public List<Product> fetchAllProducts() {
        return productQueryService.getProducts();
    }
}
