package business.book;

import business.Address;
import business.Author;
import dataaccess.DataAccessFacade;

import java.util.ArrayList;

public class BookController implements iBookController {

    public void addNewBook(Book book) {
        DataAccessFacade dataAccessFacade = new DataAccessFacade();
        dataAccessFacade.saveNewBook(book);

    }

}
