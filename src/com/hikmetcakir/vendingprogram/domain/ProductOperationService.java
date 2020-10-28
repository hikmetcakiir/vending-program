package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.domain.helper.GeneralHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;
import org.ini4j.Ini;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductOperationService {

    private Ini confReader;
    private static final String CONFIGURATION_FILE_PATH = "./src/resources/configuration/config.ini";

    public ProductOperationService(){
        confReader = new Ini();
    }

    public Map<String,Long> updateProductAmounts(String productName, Vending vending){
        Map<String,Long> productAmounts = vending.getProductAmounts();
        for(Map.Entry<String,Long> productInformation : productAmounts.entrySet()){
            if(productInformation.getKey().equals(productName))
                productAmounts.replace(productName,productInformation.getValue()-1);
        }
        return productAmounts;
    }

    public List<BigDecimal> getProductPriceFromConfFile() {
        loadConfigurationReader();
        String productPrices = confReader.get("information","product-price");
        List<String> parsedProductPriceList = GeneralHelper.parseToStringList(productPrices);
        List<BigDecimal> priceList = new ArrayList<>();
        for(String parsedValue : parsedProductPriceList){
            priceList.add(new BigDecimal(parsedValue));
        }
        return priceList;
    }

    public List<String> getProductNameFromConfFile(){
        loadConfigurationReader();
        String productNames = confReader.get("information","product-name");
        List<String> parsedProductNameList = GeneralHelper.parseToStringList(productNames);
        return parsedProductNameList;
    }

    public List<Long> getProductAmountFromConfFile() {
        loadConfigurationReader();
        String productAmounts = confReader.get("information","product-amount");
        List<String> parsedProductAmountList = GeneralHelper.parseToStringList(productAmounts);
        List<Long> productAmountList = new ArrayList<>();
        for(String parsedValue : parsedProductAmountList)
            productAmountList.add(Long.parseLong(parsedValue));
        return productAmountList;
    }

    public HashMap<String,Long> createProductCounts(){
        List<String> productNameList = new ArrayList<>();
        List<Long> productAmountList = new ArrayList<>();
        try{
            productNameList = getProductNameFromConfFile();
            productAmountList = getProductAmountFromConfFile();
        }catch (Exception exception){ }
        HashMap<String,Long> createdProductCounts = new HashMap<>();
        for(int count = 0 ; count < productAmountList.size() ; count++)
            createdProductCounts.put(productNameList.get(count),productAmountList.get(count));
        return createdProductCounts;
    }

    private void loadConfigurationReader(){
        try{confReader.load(new FileReader(CONFIGURATION_FILE_PATH)); }
        catch (Exception exception){ System.out.println("Configuration File Path Not Found!"); }
    }

    public boolean doesUserHaveProduct(User user, Product product){
        Map<String, Long> boughtProductCounts = user.getBoughtProductCounts();
        for(Map.Entry<String, Long> boughtProduct : boughtProductCounts.entrySet())
            if(boughtProduct.getKey().equals(product.getName()))
                return true;
        return false;
    }
}
