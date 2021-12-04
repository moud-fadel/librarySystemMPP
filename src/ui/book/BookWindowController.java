package ui.book;

import business.Author;
import business.Book;
import business.book.BookController;
import business.book.iBookController;
import dataaccess.DataAccessFacade;
import dataaccess.TestData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.Start;
import ui.Utils;
import ui.main.MainWindowAlternative;


import java.util.*;

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
    @FXML
    ComboBox<Author> comboAuthors;

    @FXML
    Spinner<Integer> spinnerMaxCheckout = new Spinner<>(1, 10, 1);
    SpinnerValueFactory<Integer> valueFactory = //
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
    public BookWindowController() {
    }

    @FXML
    void initialize() {
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        maxCheckoutLength.setCellValueFactory(new PropertyValueFactory<Book, Integer>("maxCheckoutLength"));

        firstName.setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
        bio.setCellValueFactory(new PropertyValueFactory<Author, String>("bio"));


        TestData testData = new TestData();
        comboAuthors.setItems(FXCollections.observableArrayList(testData.allAuthors));
        spinnerMaxCheckout.setValueFactory(valueFactory);
      /*  comboAuthors.valueProperty().addListener(new ChangeListener<Author>() {
            @Override
            public void changed(ObservableValue<? extends Author> observable, Author oldValue, Author newValue) {
                tableAuhtors.getItems().add(newValue);
             //   addAuthorToList(newValue);
            }
        });*/


    }


    @FXML
    public void close() {

        BookWindow.INSTANCE.hide();
        if (!MainWindowAlternative.INSTANCE.isShowing()) {
            MainWindowAlternative.INSTANCE.show();
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
        if (spinnerMaxCheckout.getValue().toString().equalsIgnoreCase("")) {
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
        ArrayList<Author> authorArrayList = new ArrayList<>();
        TestData d = new TestData();
        authorArrayList.addAll(tableAuhtors.getItems());
        Book book = new Book(textFieldISBN.getText().toString()
                , textFieldBookTitle.getText().toLowerCase(Locale.ROOT),
                Integer.parseInt(spinnerMaxCheckout.getValue().toString()), authorArrayList);
        iBookController iBookController = new BookController();
        iBookController.addNewBook(book);
        tableBooksData.getItems().add(book);
        tableAuhtors.getItems().clear();
        textFieldISBN.setText("");
        textFieldBookTitle.setText("");
        spinnerMaxCheckout.getValueFactory().setValue(1);
    }

    private boolean isISBNexist(String text) {
        DataAccessFacade facade = new DataAccessFacade();
        HashMap<String, Book> bookHashMap = facade.readBooksMap();
        if (bookHashMap.containsKey(text)) return true;
        return false;
    }

    @FXML
    public void addAuthor() {
        // MainWindow.hideAllWindows();
     /*   if (!AuthorWindow.INSTANCE.isInitialized()) {
            AuthorWindow.INSTANCE.init();
        }
        AuthorWindow.INSTANCE.show();*/
        if (isAuthorExist(comboAuthors.getValue())) {
            Utils.SHOW_ERROR_ALERT("You already added this author");
            return;
        }
        tableAuhtors.getItems().add(comboAuthors.getValue());

    }

    private boolean isAuthorExist(Author value) {
        if (tableAuhtors.getItems().contains(value)) return true;
        return false;
    }

    public void addAuthorToList(Author newAuthor) {
        tableAuhtors.getItems().add(newAuthor);
        // textFieldBookTitle.setText(newAuthor.getFirstName());

    }
}

