package com.hikmetcakir.vendingprogram.domain.helper;

import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.Vending;

import java.math.BigDecimal;

public class BuyOperationHelper {

    public boolean isVendingMoneyEnoughForBuyProduct(Product product, long buyAmount, Vending vending){
        BigDecimal sumAmount = product.getPrice().multiply(new BigDecimal(buyAmount));
        if(sumAmount.compareTo(vending.getMoneyAmount()) == 1)
            return false;
        else
            return true;
    }
}
