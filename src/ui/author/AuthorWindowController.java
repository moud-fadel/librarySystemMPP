package ui.author;

import business.Author;
import dataaccess.TestData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import ui.book.BookWindowController;

public class AuthorWindowController implements Initializable {
    @FXML
    TableView<Author> tableAuhtors;
    @FXML
    TableColumn<Author, String> firstName;
    @FXML
    TableColumn<Author, String> lastName;
    @FXML
    TableColumn<Author, String> bio;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TestData s = new TestData();
        firstName.setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
        bio.setCellValueFactory(new PropertyValueFactory<Author, String>("bio"));
        tableAuhtors.getItems().setAll( s.allAuthors);
        tableAuhtors.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tableAuhtors.getSelectionModel().clearSelection();
            }
            addAuthorToList(newSelection);
        });
    }

    private void addAuthorToList(Author newAuthor) {
        AuthorWindow.INSTANCE.hide();
        BookWindowController bookController = new BookWindowController();
        bookController.addAuthorToList(newAuthor);
     }
}
