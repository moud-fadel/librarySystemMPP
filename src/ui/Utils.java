package ui;

import javafx.scene.control.Alert;

public class Utils {

    static Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public static   void SHOW_ERROR_ALERT(String errorMessage) {
        errorAlert.setHeaderText(errorMessage);
        errorAlert.setTitle("Error");
        if (!errorAlert.isShowing())
            errorAlert.show();
    }
}
