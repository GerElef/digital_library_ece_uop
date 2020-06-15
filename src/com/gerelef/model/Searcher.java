package com.gerelef.model;

import com.gerelef.books.Book;

import java.util.ArrayList;
import java.util.LinkedList;

public class Searcher {

    ArrayList<Book> searchForBookAndWriter(String b, String w, ArrayList<Book> books){
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(b) && book.getWriter().equals(w)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    //https://stackoverflow.com/questions/51876081/refactoring-functions-with-nearly-entirely-duplicate-code-but-different-calls-to
    ArrayList<Book> searchForWriter(String s, ArrayList<Book> books){
        return searchForString(s, books, (book, s1) -> book.getWriter().equals(s1));
    }

    ArrayList<Book> searchForBook(String s, ArrayList<Book> books){
        return searchForString(s, books, (book, s1) -> book.getTitle().equals(s1));
    }

    private ArrayList<Book> searchForString(String s, ArrayList<Book> books, BookComparison comparison){
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (comparison.compare(book, s)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }
}
