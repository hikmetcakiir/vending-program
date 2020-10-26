package com.hikmetcakir.vendingprogram.controller;


import com.hikmetcakir.vendingprogram.domain.BuyOperationService;
import com.hikmetcakir.vendingprogram.domain.ProductOperationService;
import com.hikmetcakir.vendingprogram.domain.UserOperationService;
import com.hikmetcakir.vendingprogram.domain.helper.BuyOperationHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;

import java.math.BigDecimal;

public class BuyOperationController {

    private BuyOperationHelper buyOperationHelper;
    private BuyOperationService buyOperationService;
    private UserOperationService userOperationService;

    public BuyOperationController(Vending vending){
        buyOperationHelper = new BuyOperationHelper();
        buyOperationService = new BuyOperationService(vending);
        userOperationService = new UserOperationService();
    }

    public void buyProduct(User user, Product product, long buyAmount, Vending vending){
        if(buyOperationHelper.isVendingMoneyEnoughForBuyProduct(product,buyAmount,vending)){
            buyOperationService.buyProductFromVending(product,buyAmount);
            userOperationService.decreaseUserMoneyAmount(user,product.getPrice().multiply(new BigDecimal(buyAmount)));
        }
    }
}
