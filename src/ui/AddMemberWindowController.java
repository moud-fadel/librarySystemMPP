package ui;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ui.main.MainWindowAlternative;

import java.io.IOException;
import java.util.List;

public class AddMemberWindowController {
	
	@FXML
	private Button backButton;
	@FXML
	private Button addButton;
	@FXML
	private TextField memberIDField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField zipField;
	@FXML
	private TextField telNumField;
	@FXML
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	
	
	public void addMember(ActionEvent event) throws IOException {
		System.out.println("add member member");
		//Start.hideAllWindows();
		//Start.primStage().show();
		
		
		
		String memberID = memberIDField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String street = streetField.getText();
		String city = cityField.getText();
		String state = stateField.getText();
		String zip = zipField.getText();
		String telNum = telNumField.getText();
		if (validationError()) return;
		//boolean error = validationError(memberID)
		Address newAddress = new Address(street, city, state, zip);
		LibraryMember newMember = new LibraryMember(memberID, firstName, lastName, telNum, newAddress);
		DataAccessFacade newEntry = new DataAccessFacade();
		newEntry.saveNewMember(newMember);
		
		//System.out.println("Member Saved");
		
	}
	
	private void showErrorAlert(String errorMessage) {
        errorAlert.setHeaderText(errorMessage);
        errorAlert.setTitle("Error");
        errorAlert.show();
    }
	
	private boolean validationError() {
		
		ControllerInterface ci = new SystemController();
		List<String> ids = ci.allMemberIds();
		String memberID = memberIDField.getText();
		for(String id: ids) {
			if (memberID.equals(id)) {
				showErrorAlert("Member Id already present");
	            return true;
			}
		}
		
		
		if (AddMemberWindowController.isBlankOrNull(memberIDField.getText())) {
            showErrorAlert("Member Id empty");
            return true;
        }
        if (AddMemberWindowController.isBlankOrNull(firstNameField.getText())) {
            showErrorAlert("First Name field is empty");
            return true;
        }
        
        if (AddMemberWindowController.isBlankOrNull(lastNameField.getText())) {
            showErrorAlert("Last Name fied is empty");
            return true;
        }
        
        if (AddMemberWindowController.isBlankOrNull(streetField.getText())) {
            showErrorAlert("Street field is empty");
            return true;
        }
        
        if (AddMemberWindowController.isBlankOrNull(cityField.getText())) {
            showErrorAlert("City Field is empty");
            return true;
        }
        
        
        if (AddMemberWindowController.isBlankOrNull(stateField.getText())) {
            showErrorAlert("State Field is empty");
            return true;
        }
        
        if (AddMemberWindowController.isBlankOrNull(zipField.getText())) {
            showErrorAlert("ZIP Field is empty");
            return true;
        }
        
        if (AddMemberWindowController.isBlankOrNull(telNumField.getText())) {
            showErrorAlert("Telephone Number field is empty");
            return true;
        }
       /*
        if (!memberIdPresent(firstNameField.getText())) {
            showErrorAlert("Member Id not found");
            return true;
        }
        if (HelperUtils.isBlankOrNull(isbnTextBox.getText())) {
            showErrorAlert("ISBN empty");
            return true;
        }
        if (!isbnPresent(isbnTextBox.getText())) {
            showErrorAlert("ISBN not found");
            return true;
        }
        */
        return false;
    }
	
	public static boolean isBlankOrNull(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
	
	public void back(ActionEvent event) throws IOException {
		System.out.println("back");
		Start.hideAllWindows();
		if(!MainWindowAlternative.INSTANCE.isInitialized()) {
			MainWindowAlternative.INSTANCE.init();
		}
		MainWindowAlternative.INSTANCE.setTitle("Welcome Admin");
		MainWindowAlternative.INSTANCE.show();
		
	}
	
	
}
