package com.gerelef.books;

abstract public class Book {

    final String type;

    final String title;

    final String writer;

    final long ISBN;

    final int date;

    final String bookType;

    private final String outputFormat;

    Book(String type, String title, String writer, long ISBN, int date, String bookType){
        this.type   = type;
        this.title  = title;
        this.writer = writer;
        this.ISBN   = ISBN;
        this.date   = date;
        this.bookType = bookType;

        /* used for quickly outputting the books without any string building strain except from the initialization
         * of the object */
        outputFormat = type + "\n" + title + "\n" + writer + "\n" + ISBN + "\n" + date + "\n" + bookType + "\n";
    }

    public String getType(){
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public long getISBN() {
        return ISBN;
    }

    public int getDate() {
        return date;
    }

    public String getBookType() {
        return bookType;
    }

    public boolean equals(Book b){
        return (this.getISBN() == b.getISBN() && this.getWriter().equals(b.getWriter()));
    }

    public String getOutputFormat(){
        return outputFormat;
    }
}
