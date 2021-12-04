package ui.bookcopy;

import dataaccess.DataAccessFacade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LibWindow;

import java.io.IOException;

public class BookCopyWindow extends Stage implements LibWindow {
    public static final BookCopyWindow INSTANCE = new BookCopyWindow();

    private boolean isInitialized = false;

    @Override
    public void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BookCopyWindow.class.getResource("add_bookcopy_view.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(),800 , 600);
             setTitle("Add book copy");
             setScene(scene);
            //  show();
         } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBooksData() {
        BookCopyWindowController bookCopyWindowController = new BookCopyWindowController();
        bookCopyWindowController.loadBooksData();
    }


    public boolean isInitialized() {
        return isInitialized;
    }
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

     private BookCopyWindow() {}
}
