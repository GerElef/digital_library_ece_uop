package com.gerelef.books;

public class ScientificBook extends Book {

    private final static String TYPE = "ΕΠΙΣΤΗΜΟΝΙΚΟ";

    private final String field;

    public ScientificBook(String title, String writer, long ISBN, int date, String book_type, String field){
        super(TYPE, title, writer, ISBN, date, book_type);

        this.field = field;
    }

    public String getField() {
        return field;
    }
}
