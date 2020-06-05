package com.gerelef.gui.search;

import com.gerelef.books.Book;
import com.gerelef.model.IOLibManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Normalizer;
import java.util.List;

public class SearchDialog extends JDialog {
    private JPanel contentPane;
    private JTextField txtFldBookTitle;
    private JTextField txtFldWriterName;
    private JTextField bookTitleTextField;
    private JTextField writerNameTextField;
    private JButton btnSearch;
    private JPanel pnlBookList;

    private IOLibManager libManager = IOLibManager.getInstance();

    public SearchDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(400, 600));
        setTitle("Search for a book");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        bookTitleTextField.setOpaque(false);
        bookTitleTextField.setEditable(false);

        writerNameTextField.setOpaque(false);
        writerNameTextField.setEditable(false);

        btnSearch.addActionListener(e->{
            cleanupPanelBookList();

            //gets text from jtextfield, trims it, converts to uppercase, and normalizes it
            String bookname = Normalizer.normalize(txtFldBookTitle.getText().trim().toUpperCase(), Normalizer.Form.NFD);
            String wrtrname = Normalizer.normalize(txtFldWriterName.getText().trim().toUpperCase(), Normalizer.Form.NFD);

            inflatePanelBookList(txtFldBookTitle.getText().trim().toUpperCase(), txtFldWriterName.getText().trim().toUpperCase());
        });

        pack();
    }

    private void cleanupPanelBookList(){
        //clean up all data on panel pnlBookList
    }

    private void inflatePanelBookList(String bookTitle, String writerName){
        if (bookTitle.isEmpty() && writerName.isEmpty())
            return;

        //inflate panelBookList with data from the .txt file

        //move all this to a swing worker to inflate list
        //https://stackoverflow.com/questions/16937997/java-swingworker-thread-to-update-main-gui
        List<Book> books = IOLibManager.searchForBook(bookTitle, writerName);
        if (books == null)
            return;

        for (Book b : books){
            System.out.println("Got book " + b.getTitle());
        }
    }
}
