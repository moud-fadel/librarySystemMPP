package ui.view_checkout;

import business.Checkout;
import dataaccess.DataAccessFacade;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.checkout.CheckoutController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author kojusujan1111@gmail.com 02/12/21
 */

public class ViewCheckoutController {

    @FXML
    public TextField memberIdTextBox;
    @FXML
    public Button searchButton;
    @FXML
    public Button printButton;
    @FXML
    public TableView<Checkout> checkoutTable = new TableView<>();
    @FXML
    public TableColumn<Checkout, String> memberNameColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, String> bookTitleColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, LocalDate> checkOutDateColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, LocalDate> dueDateColumn = new TableColumn<>();
    @FXML
    public Button backButton;
    @FXML
    public TableColumn<Checkout, String> copyNoColumn;

    List<Checkout> checkoutList;
    public DataAccessFacade dataAccess = new DataAccessFacade();

    public ViewCheckoutController() {
        this.checkoutList = new ArrayList<>();
    }


    public void search() {
        String memberId = this.memberIdTextBox.getText();
        memberNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMember().getFirstName()));
        bookTitleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBookCopy().getBook().getTitle()));
        copyNoColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getBookCopy().getCopyNum())));
        checkOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        checkoutTable.setItems(getCheckOutOfTheMember(memberId));
    }

    private ObservableList<Checkout> getCheckOutOfTheMember(String memberId) {
        this.checkoutList.clear();
        HashMap<String, Checkout> checkoutHashMap = this.dataAccess.readCheckOutMap();
        checkoutHashMap.forEach((s, checkout) -> {
            if (checkout.getMember().getMemberId().equalsIgnoreCase(memberId)) {
                this.checkoutList.add(checkout);
            }
        });
        return FXCollections.observableArrayList(this.checkoutList);
    }

    public void print() {
        if (this.checkoutList.size() != 0) {
            System.out.println("------ Checkouts of " + checkoutList.get(0).getMember().getFirstName() + "-----");
            for (int i = 0; i < checkoutList.size(); i++) {
                System.out.println(i + 1);
                System.out.println("Book : " + checkoutList.get(i).getBookCopy().getBook().getTitle());
                System.out.println("Copy no : " + checkoutList.get(i).getBookCopy().getCopyNum());
                System.out.println("Checkout Date : " + checkoutList.get(i).getCheckoutDate());
                System.out.println("Due date : " + checkoutList.get(i).getDueDate());
                System.out.println("-------------------------------");
            }
            System.out.println("---------- Thank You ----------");
        }
    }

    public void back() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage viewCheckoutStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(CheckoutController.class.getResource("Checkout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        viewCheckoutStage.setTitle("Main");
        viewCheckoutStage.setScene(scene);
        viewCheckoutStage.show();
    }
}
