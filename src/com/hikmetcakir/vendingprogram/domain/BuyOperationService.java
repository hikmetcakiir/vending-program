package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.domain.helper.GeneralHelper;
import com.hikmetcakir.vendingprogram.domain.helper.MoneyOperationHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;

import java.util.Map;


public class BuyOperationService {
    private UserOperationService userOperationService;

    public BuyOperationService(){
        this.userOperationService = new UserOperationService();
    }

    public boolean buyProduct(User user, Product product,Vending vending){
        if(MoneyOperationHelper.isVendingMoneyEnough(vending,product.getPrice())){
            vending.setMoneyAmount(vending.getMoneyAmount().subtract(product.getPrice()));
            if(!userOperationService.validateDoesUserHaveProduct(user,product)){
                user.getBoughtProductCounts().put(product.getName(),1L);
                vending.getProductAmounts().replace(product.getName(),vending.getProductAmounts().get(product.getName())-1);
            }
            else{
                vending.getProductAmounts().replace(product.getName(),vending.getProductAmounts().get(product.getName())-1);
                user.getBoughtProductCounts().replace(product.getName(),user.getBoughtProductCounts().get(product.getName())+1);
            }
             return true;
        }
        return false;
    }

    public String createUserBoughtProductList(User user){
        StringBuilder stringBuilder = new StringBuilder();
        Map<String,Long> userBoughtProductCounts= user.getBoughtProductCounts();
        if(!GeneralHelper.validateNotNull(userBoughtProductCounts)) return "";
        if(userBoughtProductCounts.size() != 0)
            for(Map.Entry<String,Long> productInformation : userBoughtProductCounts.entrySet()){
                stringBuilder.append(productInformation.getKey().toUpperCase())
                        .append(" : ")
                        .append(productInformation.getValue())
                        .append(" Adet")
                        .append("\n");
            }
        else
            stringBuilder.append("Malesef hiçbir ürün almadınız!");

        return stringBuilder.toString();
    }

}
