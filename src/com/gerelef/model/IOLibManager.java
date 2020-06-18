package com.gerelef.model;

import com.gerelef.books.Book;
import com.gerelef.books.LiteraryBook;
import com.gerelef.books.ScientificBook;

import java.util.ArrayList;


/* API for the GUI to get/send data */
public class IOLibManager {
    private static IOLibManager instance = null;

    static Crawler crawler   = null;
    static Searcher searcher = null;

    private IOLibManager(){}

    public static IOLibManager getInstance() {
        if (instance == null) {
            instance = new IOLibManager();

            crawler  = new Crawler();
            searcher = new Searcher();
        }

        return instance;
    }

    public synchronized ArrayList<Book> searchForBook(String title, String writer) {
        if(title.isEmpty() && writer.isEmpty())
            throw new IllegalArgumentException();

        ArrayList<Book> books;

        if (writer.isEmpty())
            books = searcher.searchForBook(title, crawler.getAllBooks());
        else if(title.isEmpty())
            books = searcher.searchForWriter(writer, crawler.getAllBooks());
        else
            books = searcher.searchForBookAndWriter(title, writer, crawler.getAllBooks());

        return books;
    }

    public synchronized ArrayList<Book> getAllBooks(){
        return crawler.getAllBooks();
    }

    //book name + ISBN to be completely confident only 1 match will turn up
    //might be used later
    public synchronized void removeBook(String title, long ISBN) {
        ArrayList<Book> books = crawler.getAllBooks();

        for (Book book : books) {
            if (book.getISBN() == ISBN && book.getTitle().equals(title)) {
                crawler.removeBook(book);
                break;
            }
        }
    }

    public synchronized void removeBook(Book b) {
        crawler.removeBook(b);
    }

    public synchronized void addBook(String type, String title, String writer,
                                     long ISBN, int date, String bookType, String field){
        if(Helper.getLiteratureIdentifier().equals(type))
            crawler.addBook(new LiteraryBook(title, writer, ISBN, date, bookType));
        else
            crawler.addBook(new ScientificBook(title, writer, ISBN, date, bookType, field));
    }

    public void addBook(String type, String title, String writer, long ISBN, int date, String bookType){
        addBook(type, title, writer, ISBN, date, bookType, "");
    }


}
