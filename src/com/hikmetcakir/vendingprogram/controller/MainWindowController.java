package com.hikmetcakir.vendingprogram.controller;

import com.hikmetcakir.vendingprogram.domain.BuyOperationService;
import com.hikmetcakir.vendingprogram.domain.helper.GeneralHelper;
import com.hikmetcakir.vendingprogram.domain.helper.MoneyOperationHelper;
import com.hikmetcakir.vendingprogram.model.Product;
import com.hikmetcakir.vendingprogram.model.User;
import com.hikmetcakir.vendingprogram.model.Vending;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button userSumMoneyButton;

    private User user;
    private Vending vending;
    private BuyOperationService buyOperationService;

    public MainWindowController(){
        user = new User();
        vending = new Vending(new BigDecimal(0));
        buyOperationService = new BuyOperationService();
    }

    public void buyButtonAction(){
        Product product;
        if(cocaColaRadioButton.isSelected()) product = new Product("coca-cola",new BigDecimal(15));
        else if(fantaRadioButton.isSelected()) product = new Product("fanta",new BigDecimal(20));
        else product = new Product("gazoz",new BigDecimal(30));
        buyOperationService.buyProduct(user,product,vending);
        vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
    }

    public void addMoneyToVending() {
        List<BigDecimal> acknowledgedMoneyList = new ArrayList<>();
        acknowledgedMoneyList.add(new BigDecimal(0.50));
        acknowledgedMoneyList.add(new BigDecimal(1));
        acknowledgedMoneyList.add(new BigDecimal(5));
        acknowledgedMoneyList.add(new BigDecimal(10));

        if(MoneyOperationHelper.validationAddedMoneyToVending(addMoneyToVendingTextField.getText(),acknowledgedMoneyList)){
            if(MoneyOperationHelper.isUserMoneyEnough(user,GeneralHelper.convertToBigDecimal(addMoneyToVendingTextField.getText()))){
                vending.setMoneyAmount(vending.getMoneyAmount().add(GeneralHelper.convertToBigDecimal(addMoneyToVendingTextField.getText())));
                vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
                user.setMoneyAmount(user.getMoneyAmount().subtract(GeneralHelper.convertToBigDecimal(addMoneyToVendingTextField.getText())));
            }
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
            userSumMoneyButton.setVisible(false);
        }
    }

    public void resetVending(){
        userSumMoneyButton.setVisible(true);
        userSumMoneyTextField.setText("");
        addMoneyToVendingTextField.setText("");
        vending.setMoneyAmount(new BigDecimal(0));
    }

    public void cocaColaSelected(){
            sodaRadioButton.setSelected(false);
            fantaRadioButton.setSelected(false);
    }
    public void fantaSelected(){
        sodaRadioButton.setSelected(false);
        cocaColaRadioButton.setSelected(false);
    }
    public void sodaSelected(){
        fantaRadioButton.setSelected(false);
        cocaColaRadioButton.setSelected(false);
    }

}
