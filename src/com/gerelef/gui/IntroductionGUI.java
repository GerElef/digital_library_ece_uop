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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        pnlRoot = new JPanel();
        pnlRoot.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        btnExitLibrary = new JButton();
        btnExitLibrary.setText("Exit");
        btnExitLibrary.setVerticalAlignment(0);
        pnlRoot.add(btnExitLibrary, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnViewLibrary = new JButton();
        btnViewLibrary.setInheritsPopupMenu(false);
        btnViewLibrary.setText("View Library");
        btnViewLibrary.setToolTipText("Views all the available books in the library.");
        pnlRoot.add(btnViewLibrary, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnSearchLibrary = new JButton();
        btnSearchLibrary.setText("Search Library");
        btnSearchLibrary.setToolTipText("Searches through all the available books in the library.");
        pnlRoot.add(btnSearchLibrary, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnInsertLibrary = new JButton();
        btnInsertLibrary.setText("Insert Book");
        btnInsertLibrary.setToolTipText("Inserts a new book in the library.");
        pnlRoot.add(btnInsertLibrary, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlRoot;
    }
}
