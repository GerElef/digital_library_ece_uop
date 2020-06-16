package com.gerelef.gui.library;

import com.gerelef.model.IOLibManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Library dialog is responsible for showing the entire stored book list to the user. */
public class LibraryDialog extends JDialog {
    private IOLibManager libManager = IOLibManager.getInstance();

    private JPanel contentPane;
    private JButton btnRefreshList;
    private JPanel pnlBookList;

    public LibraryDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(400, 600));
        setTitle("Library Book List");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pnlBookList.setLayout(new BoxLayout(pnlBookList, BoxLayout.PAGE_AXIS));

        btnRefreshList.addActionListener(e -> {
            //clean up the ui list
            cleanupPanelBookList();
            //inflate the panel book list with the books
            inflatePanelBookList();
        });

        pack();

    }

    private void cleanupPanelBookList() {
        //clean up all data on panel pnlBookList
        pnlBookList.removeAll();
    }

    private void inflatePanelBookList() {
        //inflate panelBookList with data from the .txt file
        new Thread(new Inflator(pnlBookList)).start();
    }

}
