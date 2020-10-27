package com.hikmetcakir.vendingprogram.controller;

import com.hikmetcakir.vendingprogram.domain.BuyOperationService;
import com.hikmetcakir.vendingprogram.domain.helper.GeneralHelper;
import com.hikmetcakir.vendingprogram.domain.helper.MoneyOperationHelper;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class MainWindowController {
    @FXML
    private RadioButton cocaColaRadioButton;
    @FXML
    private RadioButton sodaRadioButton;
    @FXML
    private RadioButton fantaRadioButton;
    @FXML
    private TextField addMoneyToVendingTextField;
    @FXML
    private TextField userSumMoneyTextField;
    @FXML
    private Label vendingSumMoneyLabel;

    private User user;
    private Vending vending;
    private BuyOperationService buyOperationService;

    public MainWindowController(){
        user = new User();
        vending = new Vending(new BigDecimal(0));
        buyOperationService = new BuyOperationService(vending);
    }

    public void buyButtonAction(){
        System.out.println("Coca cola is seleceted :"+cocaColaRadioButton.isSelected());
    }

    public void addMoneyToVending() {
        List<BigDecimal> acknowledgedMoneyList = new ArrayList<>();
        acknowledgedMoneyList.add(new BigDecimal(0.50));
        acknowledgedMoneyList.add(new BigDecimal(1));
        acknowledgedMoneyList.add(new BigDecimal(5));
        acknowledgedMoneyList.add(new BigDecimal(10));

        if(MoneyOperationHelper.validationAddedMoneyToVending(addMoneyToVendingTextField.getText(),acknowledgedMoneyList)){
            vending.setMoneyAmount(vending.getMoneyAmount().add(GeneralHelper.convertToBigDecimal(addMoneyToVendingTextField.getText())));
            vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
        }
    }

    public void finishToOperation(){
        System.out.println("İşlemi Sonlandır");
    }

    public void sumUserMoney(){
        if(MoneyOperationHelper.validationAddedMoney(userSumMoneyTextField.getText())){
            user.setMoneyAmount(new BigDecimal(userSumMoneyTextField.getText()));
            addMoneyToVendingTextField.setDisable(false);
            addMoneyToVendingTextField.setText("");
            vendingSumMoneyLabel.setText("0");
        }
    }

}
