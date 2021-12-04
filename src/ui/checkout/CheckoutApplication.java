package ui.checkout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Utils;

import java.io.IOException;

/**
 * @author kojusujan1111@gmail.com 01/12/21
 */

public class CheckoutApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CheckoutController.class.getResource("Checkout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Utils.SCREE_WIDTH,  Utils.SCREE_WIDTH);
        stage.setTitle("Checkouts");
        stage.setScene(scene);
        stage.show();
    }

}
