package ui;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AdminMenu extends Stage implements LibWindow {

	public static final AdminMenu INSTANCE = new AdminMenu();

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
//		private TextArea ta;
//		public void setData(String data) {
//			ta.setText(data);
//		}

	/* This class is a singleton */
	private AdminMenu() {}

	public void init() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
			Scene scene = new Scene(root, 500, 500);
			AdminMenu.INSTANCE.setScene(scene);
			AdminMenu.INSTANCE.show();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
