package ui.book;

import business.Author;
import business.Book;
 import business.book.BookController;
import business.book.iBookController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ui.author.AuthorWindow;
import ui.main.MainWindow;

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


    public BookWindowController() {
listViewBookAuthors = new ListView<>();
    }

    @FXML
    public void addNewBook() {
       Book book = new Book(textFieldISBN.getText().toString()
       ,textFieldBookTitle.getText().toLowerCase(Locale.ROOT),
               Integer.parseInt(textMaxCheckoutNum.getText().toString()),listViewBookAuthors.getItems());
         iBookController iBookController = new BookController();
        iBookController.addNewBook(book);
    }

    @FXML
    public void addAuthor() {
       // MainWindow.hideAllWindows();
        if(!AuthorWindow.INSTANCE.isInitialized()) {
            AuthorWindow.INSTANCE.init();
        }
        AuthorWindow.INSTANCE.show();
    }

    public void addAuthorToList(Author newAuthor){
        listViewBookAuthors.getItems().add(newAuthor);

    }


}
