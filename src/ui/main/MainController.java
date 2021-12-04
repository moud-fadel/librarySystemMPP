package ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.AddMemberWindow;
import ui.book.BookWindow;
import ui.bookcopy.BookCopyWindow;
import ui.checkout.CheckoutController;
import ui.overdue.OverDueController;

import java.io.IOException;

public class MainController {
    @FXML
    public Button overDueBookButton;
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
        MainWindow.hideAllWindows();
        if (!AddMemberWindow.INSTANCE.isInitialized()) {
            AddMemberWindow.INSTANCE.init();
        }
        AddMemberWindow.INSTANCE.show();
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
     /*   Stage stage = (Stage) buttonAddCopy.getScene().getWindow();
        stage.hide();
         Stage viewCheckoutStage = new Stage();*/
        MainWindow.hideAllWindows();
        FXMLLoader fxmlLoader = new FXMLLoader(CheckoutController.class.getResource("Checkout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        MainWindow.primStage().setTitle("Checkout");
        MainWindow.primStage().setScene(scene);
        MainWindow.primStage().show();

    }

    @FXML
    public void addAddCopyHandler(ActionEvent e) {
        MainWindow.hideAllWindows();
        if (!BookCopyWindow.INSTANCE.isInitialized()) {
            BookCopyWindow.INSTANCE.init();
        }
        BookCopyWindow.INSTANCE.show();
    }


    public void overDueBook() throws IOException {
        Stage stage = (Stage) overDueBookButton.getScene().getWindow();
        stage.close();
        Stage viewCheckoutStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(OverDueController.class.getResource("overdue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        viewCheckoutStage.setTitle("Overdue Books");
        viewCheckoutStage.setScene(scene);
        viewCheckoutStage.show();
    }
}
