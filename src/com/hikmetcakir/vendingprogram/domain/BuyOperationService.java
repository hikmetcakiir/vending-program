package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.domain.helper.GeneralHelper;
import com.hikmetcakir.vendingprogram.domain.helper.MoneyOperationHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;

import java.util.Map;


public class BuyOperationService {

    private ProductOperationService productOperationService;

    public BuyOperationService(){
        this.productOperationService = new ProductOperationService();
    }

    public boolean buyProduct(User user, Product product,Vending vending){
        if(MoneyOperationHelper.isVendingMoneyEnough(vending,product.getPrice())){
            if(vending.getProductAmounts().get(product.getName()) > 0){
            vending.setMoneyAmount(vending.getMoneyAmount().subtract(product.getPrice()));
                if(!productOperationService.doesUserHaveProduct(user,product)){
                user.getBoughtProductCounts().put(product.getName(),1L);
                vending.getProductAmounts().replace(product.getName(),vending.getProductAmounts().get(product.getName())-1);
                }
                else{
                vending.getProductAmounts().replace(product.getName(),vending.getProductAmounts().get(product.getName())-1);
                user.getBoughtProductCounts().replace(product.getName(),user.getBoughtProductCounts().get(product.getName())+1);
                }
             return true;
            }else
             return false;
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
