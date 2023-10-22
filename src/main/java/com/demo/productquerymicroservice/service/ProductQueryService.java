package com.demo.productquerymicroservice.service;

import com.demo.productquerymicroservice.dto.ProductEvent;
import com.demo.productquerymicroservice.entity.Product;
import com.demo.productquerymicroservice.enums.ExceptionMessage;
import com.demo.productquerymicroservice.enums.TypeEvent;
import com.demo.productquerymicroservice.exception.ProductBusinessException;
import com.demo.productquerymicroservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductQueryService {
    private final Logger logger = LoggerFactory.getLogger(ProductQueryService.class);
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent) {
        Product product = productEvent.getProduct();
        if (productEvent.getTypeEvent().equals(TypeEvent.createdProduct.getValeur())) {
            logger.info(String.format("#### -> save create product on consumer -> %s", product));
            productRepository.save(product);
        }
        if (productEvent.getTypeEvent().equals(TypeEvent.updatedProduct.getValeur())) {
            Product existingProduct = productRepository.findById(product.getId()).orElseThrow(() -> new ProductBusinessException(ExceptionMessage.ProductNoutFound.getValeur(), product.getId()));
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            logger.info(String.format("#### -> save updated product on consumer -> %s", product));
            productRepository.save(existingProduct);
        }
    }
}
