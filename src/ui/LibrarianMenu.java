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


public class LibrarianMenu extends Stage implements LibWindow {

	public static final LibrarianMenu INSTANCE = new LibrarianMenu();

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
	private LibrarianMenu() {}

	public void init() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("LibrarianMenu.fxml"));
			Scene scene = new Scene(root, Utils.SCREE_WIDTH,  Utils.SCREE_WIDTH);
			LibrarianMenu.INSTANCE.setScene(scene);
			LibrarianMenu.INSTANCE.show();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
