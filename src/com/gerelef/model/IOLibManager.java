package com.gerelef.model;

import com.gerelef.books.Book;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class IOLibManager {
    private static IOLibManager instance = null;
    //define insertion in inserter class
    //define deletion in deleter here
    //define search in crawler class
    //maybe use this to optimize https://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java

    static Crawler crawler = new Crawler();

    private IOLibManager(){}

    public static IOLibManager getInstance() {
        if (instance == null)
            instance = new IOLibManager();

        return instance;
    }

    public static List<Book> searchForBook(String title, String writer) {
        if (title.isEmpty() && writer.isEmpty())
            throw new IllegalArgumentException();

        boolean checkForDuplicates = false;

        List<Book> books = new LinkedList<>();
        try {
            if (title.isEmpty())
                books.addAll(crawler.searchFile(writer, false));
            else if (writer.isEmpty())
                books.addAll(crawler.searchFile(title, false));
            else {
                books.addAll(crawler.searchFile(title, false));
                books.addAll(crawler.searchFile(writer, false));
                checkForDuplicates = true; //duplicates can only exist when pulling twice from the same record
            }
        } catch (Crawler.InvalidFormatException | IOException ex) {
            ex.printStackTrace();
            return null;
        }

        if (books.isEmpty())
            return null;

        if (checkForDuplicates) {
            //checks for duplicates, and removes them as such
            for(int i = 0; i < books.size(); ++i) {
                Book b = books.get(i);
                for (int j = i + 1; j < books.size(); ++j){
                    Book temp_book = books.get(j);
                    if (temp_book.equals(b)){
                        books.remove(j); // there can not be more than 1 duplicates in the records since ISBNs are unique
                        break;
                    }
                }
            }
        }

        return books;
    }


}
