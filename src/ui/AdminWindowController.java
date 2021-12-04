package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminWindowController {
	
	@FXML
	private Button addMemberButton;
	@FXML
	private Button editMemberButton;
	@FXML
	private Button addBookButton;
	
	public void addMember(ActionEvent event) throws IOException {
		System.out.println("add member");
		Start.hideAllWindows();
		//Start.primStage().show();
		
		if(!AddMemberWindow.INSTANCE.isInitialized()) {
			AddMemberWindow.INSTANCE.init();
		}
		AddMemberWindow.INSTANCE.setTitle("Add Member Window");
		AddMemberWindow.INSTANCE.show();
		System.out.println("change");
		
	}
	
	public void editMember() {
		System.out.println("edit member");
	}
	
	public void addBook() {
		System.out.println("add book");
	}
	
}
