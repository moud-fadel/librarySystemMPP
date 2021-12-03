package ui.book;

import business.Author;
import business.Book;
 import business.book.BookController;
import business.book.iBookController;
 import dataaccess.TestData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.author.AuthorWindow;
import ui.main.MainWindow;

import java.util.ArrayList;
import java.util.Locale;

public class BookWindowController {


    @FXML
    TextField textFieldISBN;
    @FXML
    TextField textFieldBookTitle;
    @FXML
    TextField textMaxCheckoutNum;
    @FXML
    TextField textFieldCopyNum;

    @FXML
    Button buttonAddAuthor;
    @FXML
    Button buttonAddBook;


    @FXML
    TableView<Author> tableAuhtors;
    @FXML
    TableColumn<Author, String> firstName;
    @FXML
    TableColumn<Author, String> lastName;
    @FXML
    TableColumn<Author, String> bio;

    @FXML
    TableView<Book> tableBooksData;
    @FXML
    TableColumn<Book, String> isbn;
    @FXML
    TableColumn<Book, String> title;
    @FXML
    TableColumn<Book, Integer> maxCheckoutLength;
    @FXML
    Button buttonClose;


    public BookWindowController() {
    }

    @FXML
    void initialize() {
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        maxCheckoutLength.setCellValueFactory(new PropertyValueFactory<Book, Integer>("maxCheckoutLength"));

    }


    @FXML
    public void close() {

        BookWindow.INSTANCE.hide();
        if (!MainWindow.primStage().isShowing()) {
            MainWindow.primStage().show();
        }
    }

    @FXML
    public void addNewBook() {
        ArrayList<Author> authorArrayList = new ArrayList<>();
        TestData d = new TestData();
        authorArrayList.add(d.allAuthors.get(0));
        Book book = new Book(textFieldISBN.getText().toString()
                , textFieldBookTitle.getText().toLowerCase(Locale.ROOT),
                Integer.parseInt(textMaxCheckoutNum.getText().toString()), authorArrayList);
        iBookController iBookController = new BookController();
        iBookController.addNewBook(book);
        tableBooksData.getItems().add(book);
    }

    @FXML
    public void addAuthor() {
        // MainWindow.hideAllWindows();
        if (!AuthorWindow.INSTANCE.isInitialized()) {
            AuthorWindow.INSTANCE.init();
        }
        AuthorWindow.INSTANCE.show();
    }

    @FXML
    public void addAuthorToList(Author newAuthor) {
        tableAuhtors.getItems().add(newAuthor);

    }
}

