package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.model.User;

import java.math.BigDecimal;

public class UserOperationService {

    public User decreaseUserMoneyAmount(User user,BigDecimal decreaseAmount){
        user.setMoneyAmount(user.getMoneyAmount().subtract(decreaseAmount));
        return user;
    }
}
