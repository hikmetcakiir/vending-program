package com.hikmetcakir.vendingprogram.controller;

import com.hikmetcakir.vendingprogram.domain.*;
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
import java.util.HashMap;
import java.util.List;


public class MainWindowController {
    @FXML private Button userSumMoneyButton;
    @FXML private Button addMoneyToVendingButton;
    @FXML private Button buyButton;
    @FXML private Button finishToOperationButton;
    @FXML private RadioButton sodaRadioButton;
    @FXML private RadioButton cocaColaRadioButton;
    @FXML private RadioButton fantaRadioButton;
    @FXML private Label cocaColaAmountLabel;
    @FXML private Label fantaAmountLabel;
    @FXML private Label vendingSumMoneyLabel;
    @FXML private Label sodaAmountLabel;
    @FXML private Label cocaColaNamePriceLabel;
    @FXML private Label fantaNamePriceLabel;
    @FXML private Label sodaNamePriceLabel;
    @FXML private TextField addMoneyToVendingTextField;
    @FXML private TextField userSumMoneyTextField;

    private User user;
    private Vending vending;
    private BuyOperationService buyOperationService;
    private ProductOperationService productOperationService;
    private MoneyOperationService moneyOperationService;

    public MainWindowController(){
        user = new User();
        user.setBoughtProductCounts(new HashMap<>());
        buyOperationService = new BuyOperationService();
        productOperationService = new ProductOperationService();
        moneyOperationService = new MoneyOperationService();
        vending = new Vending(new BigDecimal(0));
        HashMap<String ,Long> productHashMapList = productOperationService.createProductCounts();
        vending.setProductAmounts(productHashMapList);
    }

    public void buyButtonAction(){
        Product product;
        List<BigDecimal> productPriceList = productOperationService.getProductPriceFromConfFile();
        List<String> productNameList = productOperationService.getProductNameFromConfFile();

        if(cocaColaRadioButton.isSelected()) product = new Product(productNameList.get(0),productPriceList.get(0));
        else if(fantaRadioButton.isSelected()) product = new Product(productNameList.get(1),productPriceList.get(1));
        else product = new Product(productNameList.get(2),productPriceList.get(2));

        if(buyOperationService.buyProduct(user,product,vending)){
            vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
            displayUpdatedAmountProduct();
            NotificationService.showSuccessNotification("Ürün başarıyla satın alındı!");
        }else
            NotificationService.showErrorNotification("Malesef ürün satın alınamadı!");

        if(!MoneyOperationHelper.doesUserHaveMoneyInVending(vendingSumMoneyLabel.getText()))
            finishToOperation();
    }

    public void addMoneyToVending() {
        if(moneyOperationService.addMoneyToVending(user,addMoneyToVendingTextField.getText(),moneyOperationService.getAcknowledgedMoneyList(),vending)) {
            vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
            userSumMoneyTextField.setText(user.getMoneyAmount().toString());
        }
    }
    public void finishToOperation(){
        user.setMoneyAmount(user.getMoneyAmount().add(vending.getMoneyAmount()));
        vending.setMoneyAmount(new BigDecimal("0"));
        addMoneyToVendingTextField.setText("0");
        vendingSumMoneyLabel.setText(vending.getMoneyAmount().toString());
        userSumMoneyTextField.setText(user.getMoneyAmount().toString());
        NotificationService.showSuccessNotification("Yine Bekleriz. (^-^)");
        NotificationService.showUserBoughtProduct(user);
    }
    public void sumUserMoney(){
        if(MoneyOperationHelper.validationAddedMoney(userSumMoneyTextField.getText())){
            user.setMoneyAmount(new BigDecimal(userSumMoneyTextField.getText()));
            addMoneyToVendingTextField.setText("0");
            vendingSumMoneyLabel.setText("0");
            userSumMoneyButton.setVisible(false);
            openSystemInput();
            userSumMoneyTextField.setDisable(true);
            displayUpdatedAmountProduct();
            finishToOperationButton.setVisible(true);
            displayProductPrice();
        }
    }
    public void resetVending(){
        userSumMoneyButton.setVisible(true);
        userSumMoneyTextField.setText("");
        addMoneyToVendingTextField.setText("Toplam Paranızı Girin");
        vendingSumMoneyLabel.setText("Toplam Paranızı Girin");
        vending.setMoneyAmount(new BigDecimal(0));
        user.setBoughtProductCounts(new HashMap<>());
        vending.setProductAmounts(productOperationService.createProductCounts());
        displayUpdatedAmountProduct();
        userSumMoneyTextField.setDisable(false);
        closeSystemInput();
    }
    public void openSystemInput(){
        buyButton.setDisable(false);
        addMoneyToVendingTextField.setDisable(false);
        addMoneyToVendingButton.setDisable(false);
        finishToOperationButton.setDisable(false);
        cocaColaRadioButton.setSelected(true);
        fantaRadioButton.setSelected(false);
        sodaRadioButton.setSelected(false);
    }
    public void closeSystemInput(){
        buyButton.setDisable(true);
        addMoneyToVendingTextField.setDisable(true);
        addMoneyToVendingButton.setDisable(true);
        finishToOperationButton.setDisable(true);
        cocaColaRadioButton.setSelected(true);
        fantaRadioButton.setSelected(false);
        sodaRadioButton.setSelected(false);
    }
    public void displayProductPrice(){
        List<BigDecimal> priceList = productOperationService.getProductPriceFromConfFile();
        fantaNamePriceLabel.setText("Fanta ("+priceList.get(1).toString()+"₺)");
        cocaColaNamePriceLabel.setText("Coca Cola ("+priceList.get(0).toString()+"₺)");
        sodaNamePriceLabel.setText("Gazoz ("+priceList.get(2).toString()+"₺)");
    }
    public void displayUpdatedAmountProduct(){
        cocaColaAmountLabel.setText("Adet : "+vending.getProductAmounts().get("coca-cola"));
        fantaAmountLabel.setText("Adet : "+vending.getProductAmounts().get("fanta"));
        sodaAmountLabel.setText("Adet : "+vending.getProductAmounts().get("gazoz"));
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
