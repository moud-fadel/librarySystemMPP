package ui.book;

import business.Author;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.AllBooksWindow;
import ui.AllMembersWindow;
import ui.LibWindow;
import ui.LoginWindow;
import ui.main.MainWindow;

import java.io.IOException;

public class BookWindow extends Stage implements LibWindow {
    public static final BookWindow INSTANCE = new BookWindow();

    private boolean isInitialized = false;
     @Override
    public void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BookWindow.class.getResource("add_book_view.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(),900 , 600);
             setTitle("Add book");
             setScene(scene);
            //  show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isInitialized() {
        return isInitialized;
    }
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    @Override
    public void setUserData(Object value) {
        super.setUserData(value);
    }

    private BookWindow () {}

    public void addAuthor(Author newAuthor) {
        BookWindowController v = new BookWindowController();
        v.addAuthorToList(newAuthor);
    }
}
