package ui.author;

import business.Author;
import business.book.Book;
import business.book.BookController;
import business.book.iBookController;
import dataaccess.DemoData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.book.BookWindow;
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
        DemoData s = new DemoData();
        firstName.setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
        bio.setCellValueFactory(new PropertyValueFactory<Author, String>("bio"));
        tableAuhtors.getItems().setAll(s.allAuthors);
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
