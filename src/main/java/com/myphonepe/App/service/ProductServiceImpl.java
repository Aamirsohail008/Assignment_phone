package com.myphonepe.App.service;

import com.myphonepe.App.entity.Product;
import com.myphonepe.App.exception.InventoryException;
import com.myphonepe.App.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addInventory(String productId, int amount) {
        Product product = getProductByProductId(productId);
        if (product == null) {
            product = new Product();
            product.setProductId(productId);
        }
        product.setAmount(product.getAmount() + amount);
        productRepository.save(product);
    }

    @Override
    public void removeInventory(String productId) {
        Product product = getProductByProductId(productId);
        if (product == null || product.getAmount() <= 0) {
            throw new InventoryException("Inventory not available");
        }
        product.setAmount(product.getAmount() - 1);
        productRepository.save(product);
    }



    @Override
    public void removeAllInventory(String productId) {
        Product product = getProductByProductId(productId);
        if (product != null) {
            product.setAmount(0);
            productRepository.save(product);
        }
    }


    @Override
    public String viewInventory() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> product.getProductId() + ":" + product.getAmount())
                .collect(Collectors.joining(", "));
    }

    private Product getProductByProductId(String productId) {
        return productRepository.findByProductId(productId);
    }
}

