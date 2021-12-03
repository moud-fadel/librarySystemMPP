package ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.book.BookWindow;
import ui.checkout.CheckoutController;

import java.io.IOException;

public class MainController {
    @FXML
    Button buttonAddMember;
    @FXML
    Button buttonAddBook;
    @FXML
    Button buttonCheckout;
    @FXML
    Button buttonAddCopy;


    @FXML
    public void addMemberHandler(ActionEvent e) {

    }

    @FXML
    public void addBookHandler(ActionEvent e) {
        MainWindow.hideAllWindows();
        if (!BookWindow.INSTANCE.isInitialized()) {
            BookWindow.INSTANCE.init();
        }
        BookWindow.INSTANCE.show();
    }

    @FXML
    public void addCheckoutHandler(ActionEvent e) throws IOException {
        Stage stage = (Stage) buttonAddCopy.getScene().getWindow();
        stage.close();
        Stage viewCheckoutStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(CheckoutController.class.getResource("Checkout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        viewCheckoutStage.setTitle("Checkout");
        viewCheckoutStage.setScene(scene);
        viewCheckoutStage.show();

    }

    @FXML
    public void addAddCopyHandler(ActionEvent e) {

    }


}
