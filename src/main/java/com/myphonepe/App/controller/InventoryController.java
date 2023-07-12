package com.myphonepe.App.controller;

import com.myphonepe.App.dto.InventoryRequest;
import com.myphonepe.App.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final ProductService productService;

    @Autowired
    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping        // http://localhost:8080/inventory
    public ResponseEntity<Object> addInventory(@RequestBody InventoryRequest request) {
        productService.addInventory(request.getProductId(), request.getAmount());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{productId}")    // http://localhost:8080/inventory/{productId}
    public ResponseEntity<Object> removeInventory(@PathVariable String productId) {
        productService.removeInventory(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deleteAll/{productId}")      // http://localhost:8080/inventory/deleteAll/{productId}
    public ResponseEntity<Object> removeAllInventory(@PathVariable String productId) {
        productService.removeAllInventory(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping     // http://localhost:8080/inventory
    public ResponseEntity<String> viewInventory() {
        String inventory = productService.viewInventory();
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }
}

