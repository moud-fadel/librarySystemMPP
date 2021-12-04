package ui.book;

import business.Author;
import business.Book;
import business.book.BookController;
import business.book.iBookController;
import dataaccess.DataAccessFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.Utils;
import ui.author.AuthorWindow;
import ui.main.MainWindow;

import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;

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
    TableView<Author> tableAuhtors = new TableView<>();
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
        if (textFieldISBN.getText().trim().equalsIgnoreCase("")) {
            Utils.SHOW_ERROR_ALERT("Please enter book ISBN");
            return;
        }

        if (textFieldBookTitle.getText().trim().equalsIgnoreCase("")) {
            Utils.SHOW_ERROR_ALERT("Please enter book title");
            return;
        }
        if (textMaxCheckoutNum.getText().trim().equalsIgnoreCase("")) {
            Utils.SHOW_ERROR_ALERT("Please enter book max checkout count");
            return;
        }

        if (tableAuhtors.getItems().size() == 0) {

            Utils.SHOW_ERROR_ALERT("Please enter at least one author");
            return;
        }

        if (isISBNexist(textFieldISBN.getText())) {
            Utils.SHOW_ERROR_ALERT("Book is already exist");
            return;
        }
       /* ArrayList<Author> authorArrayList = new ArrayList<>();
        TestData d = new TestData();
        authorArrayList.add(d.allAuthors.get(0));*/
        Book book = new Book(textFieldISBN.getText().toString()
                , textFieldBookTitle.getText().toLowerCase(Locale.ROOT),
                Integer.parseInt(textMaxCheckoutNum.getText().toString()), tableAuhtors.getItems());
        iBookController iBookController = new BookController();
        iBookController.addNewBook(book);
        tableBooksData.getItems().add(book);
    }

    private boolean isISBNexist(String text) {
        DataAccessFacade facade = new DataAccessFacade();
        HashMap<String, Book> bookHashMap = facade.readBooksMap();
        if (bookHashMap.containsKey(isbn)) return true;
        return false;
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
        tableAuhtors.refresh();


    }
}

