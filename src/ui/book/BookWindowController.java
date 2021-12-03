package ui.book;

import business.Author;
import business.book.Book;
import business.book.BookController;
import business.book.iBookController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import ui.author.AuthorWindow;
import ui.main.MainWindow;

import java.util.ArrayList;
import java.util.List;
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
    ListView<Author> listViewBookAuthors;
    @FXML
    Button buttonAddAuthor;
    @FXML
    Button buttonAddBook;

    private ObservableList observableList = FXCollections.observableArrayList();
    private List<Author> authorsList;


    @FXML
    void initialize() {
        authorsList = new ArrayList<>();
        observableList.setAll(authorsList);
        listViewBookAuthors.setItems(observableList);

        listViewBookAuthors.setCellFactory(new Callback<ListView<Author>, ListCell<Author>>() {
            @Override
            public ListCell<Author> call(ListView<Author> param) {
                return null;
            }
        });
    }

    public BookWindowController() {
        listViewBookAuthors = new ListView<>();
    }

    @FXML
    public void addNewBook() {
        Book book = new Book(textFieldISBN.getText().toString()
                , textFieldBookTitle.getText().toLowerCase(Locale.ROOT),
                Integer.parseInt(textMaxCheckoutNum.getText().toString()), listViewBookAuthors.getItems());
        iBookController iBookController = new BookController();
        iBookController.addNewBook(book);
    }

    @FXML
    public void addAuthor() {
        // MainWindow.hideAllWindows();
        if (!AuthorWindow.INSTANCE.isInitialized()) {
            AuthorWindow.INSTANCE.init();
        }
        AuthorWindow.INSTANCE.show();
    }

    public void addAuthorToList(Author newAuthor) {
        authorsList.add(newAuthor);
        listViewBookAuthors.refresh();
        //  listViewBookAuthors.

    }


}
