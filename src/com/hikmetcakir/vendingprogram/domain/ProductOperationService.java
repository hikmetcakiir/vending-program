package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.model.Vending;

import java.util.Map;

public class ProductOperationService {

    public Map<String,Long> updateProductAmounts(String productName, long updateProductAmountValue, Vending vending){
        Map<String,Long> productAmounts = vending.getProductAmounts();
        for(Map.Entry<String,Long> productInformation : productAmounts.entrySet()){
            if(productInformation.getKey().equals(productName))
                productAmounts.replace(productName,productInformation.getValue()-updateProductAmountValue);
        }
        return productAmounts;
    }
}