package ui.main;

import dataaccess.Auth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.AllBooksWindow;
import ui.AllMembersWindow;
import ui.LoginWindow;
import ui.bookcopy.BookCopyWindow;

import java.io.IOException;

public class MainWindowCopy extends Application {
    public static final MainWindowCopy INSTANCE = new MainWindowCopy();

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

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindowCopy.class.getResource("main_view.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 800, 800);
            stage.setTitle("Main");
            stage.setScene(scene);


            if (!LoginWindow.INSTANCE.isInitialized()) {
                LoginWindow.INSTANCE.init();
            }

            stage.show();
            LoginWindow.INSTANCE.show();
            LoginWindow.INSTANCE.setResizable(false);
            LoginWindow.INSTANCE.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setCredintials(Auth credintials) {

    }

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void init() throws Exception {
        super.init();

    }


}
