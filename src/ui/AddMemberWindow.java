package ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AddMemberWindow extends Stage implements LibWindow {
	
	public static final AddMemberWindow INSTANCE = new AddMemberWindow();
		
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
		private AddMemberWindow() {}
		
		public void init() {
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("AddMemberWindow.fxml"));
				Scene scene = new Scene(root, Utils.SCREE_WIDTH,  Utils.SCREE_WIDTH);
				AddMemberWindow.INSTANCE.setScene(scene);
		        AddMemberWindow.INSTANCE.show();
		        
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

}
