package com.gerelef.books;

public class LiteraryBook extends Book {

    private final static String TYPE = "ΛΟΓΟΤΕΧΝΙΚΟ";

    public LiteraryBook(String title, String writer, long ISBN, int date, String bookType){
        super(TYPE, title, writer, ISBN, date, bookType);
    }
}
