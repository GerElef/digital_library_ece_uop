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
        pnlBookList.removeAll();
    }

    private void inflatePanelBookList(String bookTitle, String writerName) {
        if (bookTitle.isEmpty() && writerName.isEmpty())
            return;

        //inflate panelBookList with data from the .txt file
        new Thread(new Inflator(bookTitle, writerName, pnlBookList)).start();
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
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(10, 10, 10, 10), -1, -1));
        txtFldBookTitle = new JTextField();
        txtFldBookTitle.setText("");
        txtFldBookTitle.setToolTipText("Insert book name to search for.");
        contentPane.add(txtFldBookTitle, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldWriterName = new JTextField();
        txtFldWriterName.setText("");
        txtFldWriterName.setToolTipText("Insert writer name to search for.");
        contentPane.add(txtFldWriterName, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        bookTitleTextField = new JTextField();
        bookTitleTextField.setHorizontalAlignment(0);
        bookTitleTextField.setText("Book Title");
        contentPane.add(bookTitleTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        writerNameTextField = new JTextField();
        writerNameTextField.setHorizontalAlignment(0);
        writerNameTextField.setText("Writer Name");
        contentPane.add(writerNameTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pnlBookList = new JPanel();
        pnlBookList.setLayout(new BorderLayout(0, 0));
        scrollPane1.setViewportView(pnlBookList);
        btnSearch = new JButton();
        btnSearch.setText("Search");
        contentPane.add(btnSearch, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
