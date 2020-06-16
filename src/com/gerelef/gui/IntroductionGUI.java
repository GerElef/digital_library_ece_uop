package com.gerelef.gui;

import com.gerelef.gui.insert.InsertDialog;
import com.gerelef.gui.library.LibraryDialog;
import com.gerelef.gui.search.SearchDialog;

import javax.swing.*;
import java.awt.*;

/*Class that defines the introductory gui. Here, the user can select the 4 options to
 * 1. view the library, and remove books
 * 2. search the library, according to writer and/or book name
 * 3. insert books into the library, which has nested UIs */
public class IntroductionGUI extends JFrame {
    private JButton btnExitLibrary;
    private JPanel pnlRoot;
    private JButton btnViewLibrary;
    private JButton btnSearchLibrary;
    private JButton btnInsertLibrary;

    public IntroductionGUI() {
        add(pnlRoot);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 200));

        setTitle("Library Management System -- LMS");

        addListeners();

        pack();
    }

    public void addListeners() {

        /* Modal exclusion type makes the user unable to click on the main gui
        * until he is done doing on the instanciated modal. */
        //https://docs.oracle.com/javase/tutorial/uiswing/misc/modality.html
        btnViewLibrary.addActionListener(e -> {
            LibraryDialog libraryDialog = new LibraryDialog();
            libraryDialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            libraryDialog.setVisible(true);
        });

        btnSearchLibrary.addActionListener(e -> {
            SearchDialog searchDialog = new SearchDialog();
            searchDialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            searchDialog.setVisible(true);
        });

        btnInsertLibrary.addActionListener(e -> {
            InsertDialog insertDialog = new InsertDialog();
            insertDialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            insertDialog.setVisible(true);
        });

        btnExitLibrary.addActionListener(e -> {
            //dispose closes the window and unallocates/frees all pending data
            dispose();
        });
    }

}
