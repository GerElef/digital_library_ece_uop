package com.gerelef.gui.library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryDialog extends JDialog {
    private JPanel  contentPane;
    private JButton btnRefreshList;
    private JPanel  pnlBookList;

    public LibraryDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(400, 600));

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        btnRefreshList.addActionListener(e -> {
            //stop the worker if already running (add a method (?))
            cleanupPanelBookList(); //clean up list before adding stuff again
            inflatePanelBookList();
        });

        inflatePanelBookList();

        pack();

        //TO-DO: add confirmation message when clicking on the "x" remove icon-button
    }

    private void cleanupPanelBookList(){
        //clean up all data on panel pnlBookList
    }

    private void inflatePanelBookList(){
        //inflate panelBookList with data from the .txt file

        //start swing worker to inflate list
        //https://stackoverflow.com/questions/16937997/java-swingworker-thread-to-update-main-gui
    }
}
