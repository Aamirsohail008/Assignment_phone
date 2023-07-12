package com.myphonepe.App.dto;


import lombok.Data;

import java.util.Map;

@Data
public class InventoryResponse {
    private Map<String, Integer> inventory;

    public InventoryResponse(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }


}
