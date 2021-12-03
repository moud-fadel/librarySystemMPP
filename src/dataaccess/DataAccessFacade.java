package dataaccess;

import business.Book;
import business.Checkout;
import business.LibraryMember;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class DataAccessFacade implements DataAccess {

    enum StorageType {
        BOOKS, MEMBERS, USERS, CHECKOUTS;
    }

    public static final String OUTPUT_DIR = System.getProperty("user.dir") + "/src/dataaccess/storage";
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    //implement: other save operations
    public void saveNewMember(LibraryMember member) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        String memberId = member.getMemberId();
        mems.put(memberId, member);
        saveToStorage(StorageType.MEMBERS, mems);
    }
    @Override
    public void saveNewBook(Book book) {
        HashMap<String, Book> bookHashMap = readBooksMap();
        String memberId = book.getIsbn();
        bookHashMap.put(memberId, book);
        saveToStorage(StorageType.BOOKS, bookHashMap);
    }

    @Override
    public void saveCheckOut(Checkout checkout) {
        HashMap<String, Checkout> checkoutMap = readCheckOutMap();
        checkoutMap.put(checkout.getId(), checkout);
        saveToStorage(StorageType.CHECKOUTS, checkoutMap);
    }

    @Override
    public void saveBook(Book book) {
        HashMap<String, Book> bookHashMap = readBooksMap();
        bookHashMap.put(book.getIsbn(), book);
        saveToStorage(StorageType.BOOKS, bookHashMap);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Book> readBooksMap() {
        return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
    }

    @Override
    public Optional<Book> getBookWithGivenIsbn(String isbn) {
        HashMap<String, Book> bookHashMap = (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
        return bookHashMap.containsKey(isbn) ? Optional.ofNullable(bookHashMap.get(isbn)) : Optional.empty();

    }

    @SuppressWarnings("unchecked")
    public HashMap<String, LibraryMember> readMemberMap() {
        //Returns a Map with name/value pairs being
        //   memberId -> LibraryMember
        return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
    }

    public HashMap<String, Checkout> readCheckOutMap() {
        return (HashMap<String, Checkout>) readFromStorage(StorageType.CHECKOUTS);
    }


    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() {
        //Returns a Map with name/value pairs being
        //   userId -> User
        return (HashMap<String, User>) readFromStorage(StorageType.USERS);
    }


    /////load methods - these place test data into the storage area
    ///// - used just once at startup

    static void loadCheckoutMap(List<Checkout> checkouts) {
        HashMap<String, Checkout> checkoutHashMap = new HashMap<>();
        checkouts.forEach(checkout -> checkoutHashMap.put(checkout.getId(), checkout));
        saveToStorage(StorageType.CHECKOUTS, checkoutHashMap);
    }

    static void loadBookMap(List<Book> bookList) {
        HashMap<String, Book> books = new HashMap<String, Book>();
        bookList.forEach(book -> books.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOKS, books);
    }

    static void loadUserMap(List<User> userList) {
        HashMap<String, User> users = new HashMap<String, User>();
        userList.forEach(user -> users.put(user.getId(), user));
        saveToStorage(StorageType.USERS, users);
    }

    static void loadMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
        memberList.forEach(member -> members.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBERS, members);
    }

    static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (EOFException eof) {
            return new Object();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }


    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair(S s, T t) {
            first = s;
            second = t;
        }

        @Override
        public boolean equals(Object ob) {
            if (ob == null) return false;
            if (this == ob) return true;
            if (ob.getClass() != getClass()) return false;
            @SuppressWarnings("unchecked") Pair<S, T> p = (Pair<S, T>) ob;
            return p.first.equals(first) && p.second.equals(second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 5 * second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }

        private static final long serialVersionUID = 5399827794066637059L;
    }

}
