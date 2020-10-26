package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.domain.helper.BuyOperationHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.Vending;
import java.math.BigDecimal;


public class BuyOperationService {
    private Vending vending;
    private BuyOperationHelper buyOperationHelper;
    private ProductOperationService productOperationService;

    public BuyOperationService(Vending vending){
        this.vending = vending;
        this.buyOperationHelper = new BuyOperationHelper();
    }

    public void buyProductFromVending(Product product, long buyAmount){
        productOperationService.updateProductAmounts(product.getName(),buyAmount,vending);
        vending.setMoneyAmount(vending.getMoneyAmount().subtract(new BigDecimal(buyAmount)));
    }

}
