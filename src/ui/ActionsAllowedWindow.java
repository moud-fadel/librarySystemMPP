package ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class ActionsAllowedWindow extends Stage implements LibWindow {
	
	public static final ActionsAllowedWindow INSTANCE = new ActionsAllowedWindow();
		
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
		private ActionsAllowedWindow() {}
		
		public void init() {
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("ActionsAllowed.fxml"));
				Scene scene = new Scene(root, 500, 500);
				ActionsAllowedWindow.INSTANCE.setScene(scene);
		        ActionsAllowedWindow.INSTANCE.show();
		        
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

}
