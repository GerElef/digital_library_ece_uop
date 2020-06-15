package com.gerelef.books;

public class ScientificBook extends Book {

    private final static String TYPE = "ΕΠΙΣΤΗΜΟΝΙΚΟ";

    private final String field;

    public ScientificBook(String title, String writer, long ISBN, int date, String bookType, String field){
        super(TYPE, title, writer, ISBN, date, bookType);

        this.field = field;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getOutputFormat(){
        return type + "\n" + title + "\n" + writer + "\n" + ISBN + "\n" + date + "\n" + bookType + "\n" + field + "\n";
    }
}
