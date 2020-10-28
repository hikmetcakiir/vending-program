package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;

import java.util.Map;

public class UserOperationService {

    public boolean validateDoesUserHaveProduct(User user, Product product){
        Map<String, Long> boughtProductCounts = user.getBoughtProductCounts();
        for(Map.Entry<String, Long> boughtProduct : boughtProductCounts.entrySet())
            if(boughtProduct.getKey().equals(product.getName()))
                return true;
        return false;
    }

}
