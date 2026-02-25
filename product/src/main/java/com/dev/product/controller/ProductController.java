package com.dev.product.controller;

import com.dev.product.configuration.ComputeCapital;
import com.dev.product.model.ProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/produits")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<BigDecimal> getCapitalInsure(@RequestParam BigDecimal percentage,
                                                       @RequestParam ProductType type) throws Exception {
        log.info("Get Capital: {}, {}",percentage, type);
        BigDecimal capital = ComputeCapital.calculateCapital(percentage, type);
        log.info("Capital: {}", capital);
        return ResponseEntity.ok(capital);
    }
}
