package ui.main;

import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.AllBooksWindow;
import ui.book.BookWindow;

import java.util.Collections;
import java.util.List;

public class MainController {
    @FXML
    Button buttonAddMember;
    @FXML
    Button buttonAddBook;
    @FXML
    Button buttonCheckout;
    @FXML
    Button buttonAddCopy;


    @FXML
    public void addMemberHandler(ActionEvent e){

    }
  @FXML
    public void addBookHandler(ActionEvent e){
        MainWindow.hideAllWindows();
      if(!BookWindow.INSTANCE.isInitialized()) {
          BookWindow.INSTANCE.init();
      }
      BookWindow.INSTANCE.show();
    }
  @FXML
    public void addCheckoutHandler(ActionEvent e){

    }
  @FXML
    public void addAddCopyHandler(ActionEvent e){

    }



}
