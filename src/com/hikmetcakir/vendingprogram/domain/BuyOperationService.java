package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.domain.helper.BuyOperationHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;
import java.math.BigDecimal;


public class BuyOperationService {

    private Vending vending;
    private BuyOperationHelper buyOperationHelper;
    private ProductOperationService productOperationService;
    private UserOperationService userOperationService;

    public BuyOperationService(Vending vending){
        this.vending = vending;
        this.buyOperationHelper = new BuyOperationHelper();
        this.userOperationService = new UserOperationService();
    }

    public void buyProduct(User user, Product product, long buyAmount){
        if(buyOperationHelper.isVendingMoneyEnoughForBuyProduct(product,buyAmount,vending)){
            productOperationService.updateProductAmounts(product.getName(),buyAmount,vending);
            vending.setMoneyAmount(vending.getMoneyAmount().subtract(new BigDecimal(buyAmount)));
            userOperationService.decreaseUserMoneyAmount(user,product.getPrice().multiply(new BigDecimal(buyAmount)));
        }
    }

}
