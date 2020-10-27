package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;

import java.math.BigDecimal;
import java.util.Map;

public class UserOperationService {

    public User decreaseUserMoneyAmount(User user,BigDecimal decreaseAmount){
        user.setMoneyAmount(user.getMoneyAmount().subtract(decreaseAmount));
        return user;
    }

    public boolean validateDoesUserHaveProduct(User user, Product product){
        Map<String, Long> boughtProductCounts = user.getBoughtProductCounts();
        for(Map.Entry<String, Long> boughtProduct : boughtProductCounts.entrySet())
            if(boughtProduct.getKey().equals(product.getName()))
                return true;
        return false;
    }
}
