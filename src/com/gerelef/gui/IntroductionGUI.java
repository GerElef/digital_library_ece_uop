package com.gerelef.gui;

import com.gerelef.gui.insert.InsertDialog;
import com.gerelef.gui.library.LibraryDialog;
import com.gerelef.gui.search.SearchDialog;

import javax.swing.*;
import java.awt.*;

public class IntroductionGUI extends JFrame {
    private JButton btnExitLibrary;
    private JPanel pnlRoot;
    private JButton btnViewLibrary;
    private JButton btnSearchLibrary;
    private JButton btnInsertLibrary;

    public IntroductionGUI(){
        add(pnlRoot);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 200));

        setTitle("Library Management System -- LMS");

        addListeners();

        pack();
    }

    public void addListeners(){

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

        btnExitLibrary.addActionListener(e ->{
            //save anything pending here, then dispose
            dispose();
        });
    }
}
