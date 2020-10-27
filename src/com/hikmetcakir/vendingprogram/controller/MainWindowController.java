package com.hikmetcakir.vendingprogram.controller;

import com.hikmetcakir.vendingprogram.domain.BuyOperationService;
import com.hikmetcakir.vendingprogram.domain.UserOperationService;
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
import java.util.HashMap;
import java.util.List;


public class MainWindowController {
    @FXML private Button userSumMoneyButton;    @FXML private Button addMoneyToVendingButton;
    @FXML private Button buyButton;             @FXML private Button finishToOperationButton;

    @FXML private RadioButton sodaRadioButton;
    @FXML private RadioButton cocaColaRadioButton;
    @FXML private RadioButton fantaRadioButton;

    @FXML private Label cocaColaAmountLabel;    @FXML private Label fantaAmountLabel;
    @FXML private Label vendingSumMoneyLabel;   @FXML private Label sodaAmountLabel;

    @FXML private TextField addMoneyToVendingTextField;
    @FXML private TextField userSumMoneyTextField;

    private User user;
    private Vending vending;
    private BuyOperationService buyOperationService;
    private UserOperationService userOperationService;

    public MainWindowController(){
        user = new User();
        user.setBoughtProductCounts(new HashMap<>());
        buyOperationService = new BuyOperationService();
        vending = new Vending(new BigDecimal(0));

        HashMap<String ,Long> productHashMapList = new HashMap<>();
        productHashMapList.put("coca-cola",100L);
        productHashMapList.put("fanta",50L);
        productHashMapList.put("gazoz",20L);
        vending.setProductAmounts(productHashMapList);
    }

    public void buyButtonAction(){
        Product product;
        if(cocaColaRadioButton.isSelected()) product = new Product("coca-cola",new BigDecimal(15));
        else if(fantaRadioButton.isSelected()) product = new Product("fanta",new BigDecimal(20));
        else product = new Product("gazoz",new BigDecimal(30));

        if(buyOperationService.buyProduct(user,product,vending)){
            vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
            displayUpdatedAmountProduct();
        }
    }

    public void addMoneyToVending() {
        List<BigDecimal> acknowledgedMoneyList = new ArrayList<>();
        acknowledgedMoneyList.add(new BigDecimal(0.50));
        acknowledgedMoneyList.add(new BigDecimal(1));
        acknowledgedMoneyList.add(new BigDecimal(5));
        acknowledgedMoneyList.add(new BigDecimal(10));

        if(MoneyOperationHelper.validationAddedMoneyToVending(addMoneyToVendingTextField.getText(),acknowledgedMoneyList)){
            BigDecimal addedMoneyToVending = GeneralHelper.convertToBigDecimal(addMoneyToVendingTextField.getText());
            if(MoneyOperationHelper.isUserMoneyEnough(user,addedMoneyToVending)){
                vending.setMoneyAmount(vending.getMoneyAmount().add(addedMoneyToVending));
                vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
                user.setMoneyAmount(user.getMoneyAmount().subtract(addedMoneyToVending));
                userSumMoneyTextField.setText(user.getMoneyAmount().toString());
            }
        }
    }
    public void finishToOperation(){
        user.setMoneyAmount(user.getMoneyAmount().add(vending.getMoneyAmount()));
        vending.setMoneyAmount(new BigDecimal("0"));
        vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
        userSumMoneyTextField.setText(user.getMoneyAmount().toString());
    }
    public void sumUserMoney(){
        if(MoneyOperationHelper.validationAddedMoney(userSumMoneyTextField.getText())){
            user.setMoneyAmount(new BigDecimal(userSumMoneyTextField.getText()));
            addMoneyToVendingTextField.setDisable(false);
            addMoneyToVendingTextField.setText("0");
            vendingSumMoneyLabel.setText("0");
            userSumMoneyButton.setVisible(false);
            openSystemInput();
            displayUpdatedAmountProduct();
        }
    }
    public void displayUpdatedAmountProduct(){
        cocaColaAmountLabel.setText("Adet : "+vending.getProductAmounts().get("coca-cola"));
        fantaAmountLabel.setText("Adet : "+vending.getProductAmounts().get("fanta"));
        sodaAmountLabel.setText("Adet : "+vending.getProductAmounts().get("gazoz"));
    }
    public void resetVending(){
        userSumMoneyButton.setVisible(true);
        userSumMoneyTextField.setText("");
        addMoneyToVendingTextField.setText("Toplam Paranızı Girin");
        vendingSumMoneyLabel.setText("Toplam Paranızı Girin");
        vending.setMoneyAmount(new BigDecimal(0));
        closeSystemInput();
    }
    public void openSystemInput(){
        buyButton.setDisable(false);
        addMoneyToVendingTextField.setDisable(false);
        addMoneyToVendingButton.setDisable(false);
        finishToOperationButton.setDisable(false);
    }
    public void closeSystemInput(){
        buyButton.setDisable(true);
        addMoneyToVendingTextField.setDisable(true);
        addMoneyToVendingButton.setDisable(true);
        finishToOperationButton.setDisable(true);
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
