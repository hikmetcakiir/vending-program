package com.hikmetcakir.vendingprogram.model;

import java.math.BigDecimal;
import java.util.Map;

public class Vending {
    private BigDecimal moneyAmount;
    private Map<String,Long> productAmounts;

    public Vending() {
    }

    public Vending(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Map<String, Long> getProductAmounts() {
        return productAmounts;
    }

    public void setProductAmounts(Map<String, Long> productAmounts) {
        this.productAmounts = productAmounts;
    }
}
