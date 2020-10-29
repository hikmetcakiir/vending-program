package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.domain.helper.GeneralHelper;
import com.hikmetcakir.vendingprogram.domain.helper.MoneyOperationHelper;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;
import org.ini4j.Ini;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MoneyOperationService {

    public boolean addMoneyToVending(User user, String addedMoneyAmount, List<BigDecimal> acknowledgedMoneyList, Vending vending){
        if(MoneyOperationHelper.validationAddedMoneyToVending(addedMoneyAmount,acknowledgedMoneyList)){
            BigDecimal addedMoneyToVending = GeneralHelper.convertToBigDecimal(addedMoneyAmount);
            if(MoneyOperationHelper.isUserMoneyEnough(user,addedMoneyToVending)){
                vending.setMoneyAmount(vending.getMoneyAmount().add(addedMoneyToVending));
                user.setMoneyAmount(user.getMoneyAmount().subtract(addedMoneyToVending));
                return true;
            }else
                NotificationService.showErrorNotification("Malesef yeterli paranız bulunmamaktadır.");
        }else
            NotificationService.showErrorNotification("Otomat sadece 0.50, 1,5 ve 10 Lira kabul etmektedir.");
        return false;
    }

    public List<BigDecimal> getAcknowledgedMoneyList(){
        Ini confReader = new Ini();
        InputStream url = this.getClass().getResourceAsStream("/resources/configuration/config.ini");
        try{ confReader.load(new InputStreamReader(url)); }
        catch (Exception exception){ System.out.println("Configuration File Path Not Found!"); }
        String acknowledgedMoney = confReader.get("information","acknowledged-money");
        List<String> parsedAcknowledgedMoneyList = GeneralHelper.parseToStringList(acknowledgedMoney);
        List<BigDecimal> acknowledgedMoneyList = new ArrayList<>();
        for(String parsedValue : parsedAcknowledgedMoneyList){
            acknowledgedMoneyList.add(new BigDecimal(parsedValue));
        }
        return acknowledgedMoneyList;
    }

}
