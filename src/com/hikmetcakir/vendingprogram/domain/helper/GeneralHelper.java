package com.hikmetcakir.vendingprogram.domain.helper;

import java.math.BigDecimal;

public class GeneralHelper {
    public static BigDecimal convertToBigDecimal(String incomingValue){
        if(incomingValue == null) return null;
        try{ return new BigDecimal(incomingValue); }
        catch (Exception exception){     }
        return null;
    }
    public static boolean validateNotEmpty(String incomingValue){
        if(incomingValue != null)
            return true;
        return false;
    }

    public static boolean validateNotNegative(BigDecimal incomingValue){
        if(incomingValue.compareTo(new BigDecimal(0)) == 0 ||
             incomingValue.compareTo(new BigDecimal(0)) == 1){
            return true;
        }
        return false;
    }
}
