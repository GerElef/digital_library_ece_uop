package com.gerelef.books;

abstract public class Book {

    final String type;

    final String title;

    final String writer;

    final long ISBN;

    final int date;

    final String book_type;

    Book(String type, String title, String writer, long ISBN, int date, String book_type){
        this.type   = type;
        this.title  = title;
        this.writer = writer;
        this.ISBN   = ISBN;
        this.date   = date;
        this.book_type = book_type;
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

    public String getBook_type() {
        return book_type;
    }

    public boolean equals(Book b){
        return this.getISBN() == b.getISBN();
    }
}
