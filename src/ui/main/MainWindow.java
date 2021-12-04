package ui.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.AllBooksWindow;
import ui.AllMembersWindow;
import ui.LibWindow;
import ui.LoginWindow;
import ui.book.BookWindow;
import ui.bookcopy.BookCopyWindow;

import java.io.IOException;

public class MainWindow extends Application {
    public static final MainWindow INSTANCE = new MainWindow();

    private boolean isInitialized = false;

    private static Stage primStage = null;

    public static Stage primStage() {
        return primStage;
    }

    public static class Colors {
        static Color green = Color.web("#034220");
        static Color red = Color.FIREBRICK;
    }

    private static Stage[] allWindows = {
            LoginWindow.INSTANCE,
            AllMembersWindow.INSTANCE,
            AllBooksWindow.INSTANCE,
            BookCopyWindow.INSTANCE

    };


    public static void hideAllWindows() {
        primStage.hide();
        for (Stage st : allWindows) {
            st.hide();
        }
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }


    @Override
    public void start(Stage stage) throws Exception {
        try {
            primStage = stage;

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("main_view.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(),800 , 800);
            stage.setTitle("Main");
            stage.setScene(scene);


            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void init() throws Exception {
        super.init();

    }


}
