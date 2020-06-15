package com.gerelef.gui.library;

import com.gerelef.model.IOLibManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryDialog extends JDialog {
    IOLibManager libManager = IOLibManager.getInstance();

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
            //stop the worker if already running (add a method (?))
            cleanupPanelBookList(); //clean up list before adding stuff again
            inflatePanelBookList();
        });

        pack();

        //TO-DO: add confirmation message when clicking on the "x" remove icon-button
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
