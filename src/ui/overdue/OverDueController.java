package ui.overdue;

import business.Checkout;
import dataaccess.DataAccessFacade;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.Start;
import ui.main.MainController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author kojusujan1111@gmail.com 03/12/21
 */

public class OverDueController {
    @FXML
    public TextField isbnTextField;
    @FXML
    public Button searchButton;
    @FXML
    public TableView<Checkout> overDueTable = new TableView<>();
    @FXML
    public TableColumn<Checkout, String> isbnColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, String> bookColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, String> copyNoColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, String> memberColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, LocalDate> checkoutDateColumn = new TableColumn<>();
    @FXML
    public TableColumn<Checkout, LocalDate> dueDateColumn = new TableColumn<>();

    public DataAccessFacade dataAccess = new DataAccessFacade();
    @FXML
    public Button backButton;

    public OverDueController() {
    }

    public void back() throws IOException {
       /* Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage viewCheckoutStage = new Stage();*/
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("main_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
      Start.primStage().setTitle("Overdue");
      Start.primStage().setScene(scene);
      Start.primStage().show();
    }

    public void search() {
        isbnColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBookCopy().getBook().getIsbn()));
        bookColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBookCopy().getBook().getTitle()));
        copyNoColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getBookCopy().getCopyNum())));
        memberColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getMember().getFirstName())));
        checkoutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        List<Checkout> overDueList = new ArrayList<>();
        HashMap<String, Checkout> checkoutHashMap = dataAccess.readCheckOutMap();
        checkoutHashMap.forEach((s, checkout) -> {
            if (this.isbnTextField.getText().equalsIgnoreCase(checkout.getBookCopy().getBook().getIsbn())
                    && checkout.getDueDate().isAfter(LocalDate.now().minusDays(30))) {
                overDueList.add(checkout);
            }
        });
        overDueTable.setItems(FXCollections.observableArrayList(overDueList));
        overDueTable.refresh();
    }
}
