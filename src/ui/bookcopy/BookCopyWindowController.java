package ui.bookcopy;

import business.Author;
import business.Book;
import business.BookCopy;
import business.book.BookController;
import business.book.iBookController;
import dataaccess.DataAccessFacade;
import dataaccess.TestData;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.author.AuthorWindow;
import ui.main.MainWindow;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BookCopyWindowController {


    @FXML
    TextField textFieldISBN;
    @FXML
    TextField textFieldBookTitle;
    @FXML
    TextField textMaxCheckoutNum;
    @FXML
    TextField textFieldCopyNum;


    @FXML
    TableView<Book> tableBooksData;
    @FXML
    TableColumn<Book, String> isbn;
    @FXML
    TableColumn<Book, String> title;
    @FXML
    TableColumn<Book, Integer> maxCheckoutLength;


    @FXML
    TableView<BookCopy> tableBooksCopyData;

     @FXML
    TableColumn<BookCopy, Integer> copyNum;

    @FXML
    Button buttonClose;
    @FXML
    Button buttonAddCopy;


    public BookCopyWindowController() {
    }

    @FXML
    void initialize() {
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        maxCheckoutLength.setCellValueFactory(new PropertyValueFactory<Book, Integer>("maxCheckoutLength"));

         copyNum.setCellValueFactory(new PropertyValueFactory<BookCopy, Integer>("copyNum"));
        loadBooksData();


        tableBooksData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
                tableBooksCopyData.getItems().setAll(observable.getValue().getCopies());
            }
        });

        buttonAddCopy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Book newCopy = tableBooksData.getSelectionModel().getSelectedItem();
                newCopy.addCopy();
                tableBooksCopyData.getItems().add(newCopy.getCopies()[newCopy.getCopies().length-1]);

                //save to database
                DataAccessFacade dataAccessFacade = new DataAccessFacade();
                dataAccessFacade.saveBook(newCopy);
                tableBooksCopyData.refresh();
            }
        });
    }


    public void loadBooksData() {
        DataAccessFacade dataAccessFacade = new DataAccessFacade();
        dataAccessFacade.readBooksMap();
        List<Book> list = new ArrayList<Book>(dataAccessFacade.readBooksMap().values());
        tableBooksData.getItems().setAll(list);

    }

    @FXML
    public void close() {

        BookCopyWindow.INSTANCE.hide();
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


}

