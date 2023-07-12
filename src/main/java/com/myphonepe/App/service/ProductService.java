package com.myphonepe.App.service;

public interface ProductService {
    void addInventory(String productId, int amount);
    void removeInventory(String productId);
    void removeAllInventory(String productId);
    String viewInventory();
}

