package com.hikmetcakir.vendingprogram.domain;

import com.hikmetcakir.vendingprogram.model.User;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;



public class NotificationService {

    private static final String SUCCESS_IMG_PATH = "/resources/image/notification-img/success-img.png";
    private static final String ERROR_IMG_PATH = "/resources/image/notification-img/error-img.png";
    private static final String INFORMATION_IMG_PATH = "/resources/image/notification-img/information-img.png";

    public static void showSuccessNotification(String successMessage){
        Notifications notificationBuilder = Notifications.create()
                .title("İşlem Başarılı")
                .text(successMessage)
                .graphic(new ImageView(new Image(SUCCESS_IMG_PATH)))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
        notificationBuilder.show();
    }

    public static void showErrorNotification(String errorMessage){
        Notifications notificationBuilder = Notifications.create()
                .title("İşlem Başarısız")
                .text(errorMessage)
                .graphic(new ImageView(new Image(ERROR_IMG_PATH)))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
        notificationBuilder.show();
    }

    public static void showUserBoughtProduct(User user){
        BuyOperationService buyOperationService = new BuyOperationService();
        Notifications notificationBuilder = Notifications.create()
                .title("Satın Alınan Ürünler")
                .text(buyOperationService.createUserBoughtProductList(user))
                .graphic(new ImageView(new Image(INFORMATION_IMG_PATH)))
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER);
        notificationBuilder.show();
    }
}
