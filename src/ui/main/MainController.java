package ui.main;

import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.*;
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
    Button buttonLogout;

    @FXML
    void initialize() {

        if (MainWindowAlternative.INSTANCE.credintials == Auth.LIBRARIAN) {
            buttonAddBook.setVisible(false);
            buttonAddMember.setVisible(false);
            buttonAddCopy.setVisible(false);
            overDueBookButton.setVisible(false);
            buttonCheckout.setVisible(false);
            buttonCheckout.setVisible(true);


        } else if (MainWindowAlternative.INSTANCE.credintials == Auth.ADMIN) {
            buttonAddBook.setVisible(true);
            buttonAddMember.setVisible(true);
            buttonAddCopy.setVisible(true);
            overDueBookButton.setVisible(false);
            buttonCheckout.setVisible(false);

        } else {
            buttonAddBook.setVisible(true);
            buttonAddMember.setVisible(true);
            buttonAddCopy.setVisible(true);
            overDueBookButton.setVisible(true);
            buttonCheckout.setVisible(true);
        }
     }

    @FXML
    public void logout() {
        Start.hideAllWindows();
        if(!LoginWindow.INSTANCE.isInitialized()) {
            LoginWindow.INSTANCE.init();
        }
        LoginWindow.INSTANCE.clear();
        LoginWindow.INSTANCE.show();

    }
    @FXML
    public void addMemberHandler(ActionEvent e) {
        Start.hideAllWindows();
        if (!AddMemberWindow.INSTANCE.isInitialized()) {
            AddMemberWindow.INSTANCE.init();
        }
        AddMemberWindow.INSTANCE.show();
    }

    @FXML
    public void addBookHandler(ActionEvent e) {
        Start.hideAllWindows();
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
        Start.hideAllWindows();
        FXMLLoader fxmlLoader = new FXMLLoader(CheckoutController.class.getResource("Checkout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Utils.SCREE_WIDTH,  Utils.SCREE_WIDTH);
        Start.primStage().setTitle("Checkout");
        Start.primStage().setScene(scene);
        Start.primStage().show();

    }

    @FXML
    public void addAddCopyHandler(ActionEvent e) {
        Start.hideAllWindows();
        if (!BookCopyWindow.INSTANCE.isInitialized()) {
            BookCopyWindow.INSTANCE.init();
        }
        BookCopyWindow.INSTANCE.show();
    }


    public void overDueBook() throws IOException {
       /* Stage stage = (Stage) overDueBookButton.getScene().getWindow();
        stage.close();
        Stage viewCheckoutStage = new Stage();*/
        FXMLLoader fxmlLoader = new FXMLLoader(OverDueController.class.getResource("overdue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Utils.SCREE_WIDTH,  Utils.SCREE_WIDTH);
        Start.primStage().setTitle("Overdue Books");
        Start.primStage().setScene(scene);
        Start.primStage().show();
    }


}
