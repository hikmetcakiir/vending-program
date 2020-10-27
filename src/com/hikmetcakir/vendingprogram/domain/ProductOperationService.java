package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.model.Vending;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductOperationService {

    public Map<String ,Long> getProductInfForVending(){
        return null;
    }

    public List<BigDecimal> getProductPriceValueForVending(){
        return null;
    }

    public Map<String,Long> updateProductAmounts(String productName, Vending vending){
        Map<String,Long> productAmounts = vending.getProductAmounts();
        for(Map.Entry<String,Long> productInformation : productAmounts.entrySet()){
            if(productInformation.getKey().equals(productName))
                productAmounts.replace(productName,productInformation.getValue()-1);
        }
        return productAmounts;
    }
}
