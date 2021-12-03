package ui.author;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LibWindow;

import java.io.IOException;

public class AuthorWindow extends Stage implements LibWindow {
    public static final AuthorWindow INSTANCE = new AuthorWindow();

    private boolean isInitialized = false;

    @Override
    public void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AuthorWindow.class.getResource("author_view.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(),300 , 700);
             setTitle("Authors");
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

     private AuthorWindow() {}
}
