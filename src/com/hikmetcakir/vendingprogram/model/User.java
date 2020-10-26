package com.hikmetcakir.vendingprogram.model;

import java.math.BigDecimal;
import java.util.Map;

public class User {
    private BigDecimal moneyAmount;
    Map<String,Long> boughtProductCounts;

    public User() {
    }

    public User(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Map<String, Long> getBoughtProductCounts() {
        return boughtProductCounts;
    }

    public void setBoughtProductCounts(Map<String, Long> boughtProductCounts) {
        this.boughtProductCounts = boughtProductCounts;
    }
}
