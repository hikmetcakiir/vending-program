package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.domain.helper.MoneyOperationHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;


public class BuyOperationService {

    private ProductOperationService productOperationService;
    private UserOperationService userOperationService;

    public BuyOperationService(){
        this.productOperationService = new ProductOperationService();
        this.userOperationService = new UserOperationService();
    }

    public boolean buyProduct(User user, Product product,Vending vending){
        if(MoneyOperationHelper.isVendingMoneyEnough(vending,product.getPrice())){
            vending.setMoneyAmount(vending.getMoneyAmount().subtract(product.getPrice()));
            if(!userOperationService.validateDoesUserHaveProduct(user,product))
                user.getBoughtProductCounts().put(product.getName(),1L);
            else
                user.setBoughtProductCounts(productOperationService.updateProductAmounts(product.getName(),vending));
            return true;
        }
        return false;
    }

}
