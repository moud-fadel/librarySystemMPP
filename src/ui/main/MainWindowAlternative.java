package ui.main;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LibWindow;

import java.io.IOException;


public class MainWindowAlternative extends Stage implements LibWindow {

	public static final MainWindowAlternative INSTANCE = new MainWindowAlternative();

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
	private MainWindowAlternative() {}

	public void init() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
			Scene scene = new Scene(root, 500, 500);
			MainWindowAlternative.INSTANCE.setScene(scene);
			MainWindowAlternative.INSTANCE.show();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
