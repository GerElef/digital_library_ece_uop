package com.gerelef.model;

import com.gerelef.books.Book;
import com.gerelef.books.LiteraryBook;
import com.gerelef.books.ScientificBook;

import java.util.ArrayList;

import static com.gerelef.model.Helper.*;
import static com.gerelef.model.Helper.validateType;

public class IOLibManager {
    private static IOLibManager instance = null;
    //define insertion in inserter class
    //define deletion in deleter here
    //define search in crawler class
    //maybe use this to optimize https://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java

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

    public synchronized static ArrayList<Book> searchForBook(String title, String writer) {
        if(title.isBlank() && writer.isBlank())
            throw new IllegalArgumentException();

        ArrayList<Book> books;
        if (writer.isBlank())
            books = searcher.searchForBook(title, crawler.getAllBooks());
        else if(title.isBlank())
            books = searcher.searchForWriter(writer, crawler.getAllBooks());
        else
            books = searcher.searchForBookAndWriter(title, writer, crawler.getAllBooks());

        return books;
    }

    public synchronized ArrayList<Book> getAllBooks(){
        return crawler.getAllBooks();
    }

    //book name + ISBN to be completely confident only 1 match will turn up
    public synchronized void removeBook(String title, long ISBN) {
        ArrayList<Book> books = crawler.getAllBooks();

        for (Book book : books) {
            if (book.getISBN() == ISBN && book.getTitle().equals(title)) {
                crawler.removeBook(book);
                break;
            }
        }
    }

    public synchronized void addBook(String typeStr, String title, String writer,
                                     String ISBNStr, String dateStr, String bookType, String field){
        try{
            String type = normalizeGreek(typeStr).toUpperCase();

            title = title.trim();
            writer = writer.trim();
            long ISBN = convertISBN(ISBNStr.trim());
            int date = convertDate(dateStr.trim());

            String[] fields;
            if(Helper.getLiteratureIdentifier().equals(type)) {
                fields = new String[]{"ΜΥΘΙΣΤΟΡΗΜΑ", "ΝΟΥΒΕΛΑ", "ΔΙΗΓΗΜΑ", "ΠΟΙΗΣΗ"};
                bookType = validateType(normalizeGreek(bookType.trim().toUpperCase()), fields);
                crawler.addBook(new LiteraryBook(title, writer, ISBN, date, bookType));
            }
            else {
                fields = new String[]{"ΠΕΡΙΟΔΙΚΟ", "ΒΙΒΛΙΟ", "ΠΡΑΚΤΙΚΑ ΣΥΝΕΔΡΙΩΝ"};
                bookType = validateType(normalizeGreek(bookType.trim().toUpperCase()), fields);
                crawler.addBook(new ScientificBook(title, writer, ISBN, date, bookType, field));
            }

        } catch (Crawler.InvalidFormatException e) {
            e.printStackTrace();
        }

    }

    public void addBook(String type, String title, String writer, String ISBN, String date, String bookType){
        addBook(type, title, writer, ISBN, date, bookType, "");
    }


}
