package com.gerelef.model;

import com.gerelef.books.Book;

@FunctionalInterface
interface BookComparison {
    boolean compare(Book book, String s);
}
