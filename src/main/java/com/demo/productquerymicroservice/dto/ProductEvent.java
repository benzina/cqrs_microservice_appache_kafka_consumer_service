package com.demo.productquerymicroservice.dto;

import com.demo.productquerymicroservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private String typeEvent;
    private Product product;
}
