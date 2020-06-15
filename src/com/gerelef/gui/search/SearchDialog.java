package com.gerelef.gui.search;

import com.gerelef.books.Book;
import com.gerelef.model.Helper;
import com.gerelef.model.IOLibManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Normalizer;
import java.util.ArrayList;

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

        btnSearch.addActionListener(e -> {
            cleanupPanelBookList();

            //gets text from jtextfield, trims it, converts to uppercase, and normalizes it
            String bookname = Helper.normalizeGreek(txtFldBookTitle.getText().trim()).toUpperCase();
            String wrtrname = Helper.normalizeGreek(txtFldWriterName.getText().trim()).toUpperCase();

            inflatePanelBookList(bookname, wrtrname);
        });

        pnlBookList.setLayout(new BoxLayout(pnlBookList, BoxLayout.PAGE_AXIS));

        pack();
    }

    private void cleanupPanelBookList() {
        //clean up all data on panel pnlBookList
    }

    private void inflatePanelBookList(String bookTitle, String writerName) {
        if (bookTitle.isBlank() && writerName.isBlank())
            return;

        //inflate panelBookList with data from the .txt file
        new Thread(new Inflator(bookTitle, writerName, pnlBookList)).start();
    }

}
