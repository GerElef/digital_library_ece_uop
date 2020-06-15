package com.gerelef.gui.search;

import com.gerelef.books.Book;
import com.gerelef.model.IOLibManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

class Inflator implements Runnable {
    JPanel guipanel;

    String title;
    String writer;

    Inflator(String t, String w, JPanel panel){
        guipanel = panel;
        title = t;
        writer = w;
    }

    @Override
    public void run() {
        ArrayList<Book> books = IOLibManager.searchForBook(title, writer);
        if (books == null)
            return;

        for (Book b : books){
            JPanel container = new JPanel();
            JPanel container1 = new JPanel();
            JPanel container2 = new JPanel();
            JPanel container3 = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
            container1.setLayout(new BoxLayout(container1, BoxLayout.LINE_AXIS));
            container2.setLayout(new BoxLayout(container2, BoxLayout.LINE_AXIS));
            container3.setLayout(new BoxLayout(container3, BoxLayout.LINE_AXIS));

            container.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JTextPane type = new JTextPane();
            JTextPane title = new JTextPane();
            JTextPane writer = new JTextPane();
            JTextPane bookType = new JTextPane();
            JTextPane ISBN = new JTextPane();
            JTextPane date = new JTextPane();
            type.setText(b.getType());
            title.setText(b.getTitle());
            writer.setText(b.getWriter());
            bookType.setText(b.getBookType());
            ISBN.setText(b.getISBN() + "");
            date.setText(b.getDate() + "");

            container1.add(type);
            container1.add(title);
            container2.add(writer);
            container2.add(bookType);
            container3.add(ISBN);
            container3.add(date);

            container.add(container1);
            container.add(container2);
            container.add(container3);
            guipanel.add(container);
        }

        guipanel.revalidate();
    }
}
