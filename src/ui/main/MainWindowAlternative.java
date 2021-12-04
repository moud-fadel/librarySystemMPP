package ui.main;


import dataaccess.Auth;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LibWindow;
import ui.Utils;

import java.io.IOException;


public class MainWindowAlternative extends Stage implements LibWindow {

	public static final MainWindowAlternative INSTANCE = new MainWindowAlternative();

	private boolean isInitialized = false;

 public  Auth credintials;
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
	private MainWindowAlternative() {}

	public void init() {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
			Scene scene = new Scene(root, Utils.SCREE_WIDTH, Utils.SCREE_WIDTH);
			MainWindowAlternative.INSTANCE.setScene(scene);
			MainWindowAlternative.INSTANCE.show();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
