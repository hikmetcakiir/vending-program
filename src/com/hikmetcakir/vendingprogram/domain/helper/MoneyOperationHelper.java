package com.hikmetcakir.vendingprogram.domain.helper;

import java.math.BigDecimal;
import java.util.List;

public class MoneyOperationHelper {

    public static boolean validationAddedMoneyToVending(String incomingValue,List<BigDecimal> acknowledgedMoneyList){
        if(validationAddedMoney(incomingValue))
            if(validationAddedMoneyToVending(GeneralHelper.convertToBigDecimal(incomingValue),acknowledgedMoneyList) )
                return true;
        return false;
    }
    public static boolean validationAddedMoney(String incomingValue){
        if (GeneralHelper.validateNotEmpty(incomingValue))
            if (GeneralHelper.convertToBigDecimal(incomingValue) != null)
                if(GeneralHelper.validateNotNegative(GeneralHelper.convertToBigDecimal(incomingValue)))
                    return  true;
        return  false;
    }
    private static boolean validationAddedMoneyToVending(BigDecimal addedMoney, List<BigDecimal> moneyAmountList){
        boolean moneyEqualControlValue = false;
        for(BigDecimal moneyAmount : moneyAmountList){
            if(moneyAmount.compareTo(addedMoney) == 0)
                moneyEqualControlValue = true;
        }
        return moneyEqualControlValue;
    }
}
