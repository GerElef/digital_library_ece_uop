package com.gerelef.gui.insert;

import com.gerelef.model.Helper;
import com.gerelef.model.IOLibManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Responsible for inserting scientific books */
public class InsertScientificBookDialog extends JDialog {
    private JPanel contentPane;
    private JTextField txtFldBookTitle;
    private JTextField txtFldWriterName;
    private JTextField txtFldISBN;
    private JTextField txtFldDate;
    private JRadioButton rdBtnLogging;
    private JRadioButton rdBtnBook;
    private JRadioButton rdBtnMagazine;
    private JTextArea txtPaneFreetext;
    private JTextField txtFldExpl;
    private JTextField txtFldExpl2;
    private JTextField txtFldExpl3;
    private JTextField txtFldExpl4;
    private JTextField txtFldExpl5;
    private JTextField txtFldExpl6;
    private JButton btnDone;

    IOLibManager libManager = IOLibManager.getInstance();

    public InsertScientificBookDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(600, 400));
        setTitle("Insert Scientific Book");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        txtFldExpl.setEditable(false);
        txtFldExpl2.setEditable(false);
        txtFldExpl3.setEditable(false);
        txtFldExpl4.setEditable(false);
        txtFldExpl5.setEditable(false);
        txtFldExpl6.setEditable(false);

        txtFldExpl.setOpaque(true);
        txtFldExpl2.setOpaque(true);
        txtFldExpl3.setOpaque(true);
        txtFldExpl4.setOpaque(true);
        txtFldExpl5.setOpaque(true);
        txtFldExpl6.setOpaque(true);

        txtPaneFreetext.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtPaneFreetext.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        txtPaneFreetext.setLineWrap(true);

        btnDone.addActionListener(e -> {
            if (userIsDone()) {
                new Thread(() -> {
                    //normalizes greek, uppercases it, trims it
                    String title = Helper.normalizeGreek(txtFldBookTitle.getText()).toUpperCase().trim();
                    String writer = Helper.normalizeGreek(txtFldWriterName.getText()).toUpperCase().trim();
                    long ISBN = Long.parseLong(txtFldISBN.getText()); //this should be okay since we've checked it b4
                    int date = Integer.parseInt(txtFldDate.getText()); //this should be okay since we've checked it b4
                    String freetext = txtPaneFreetext.getText(); //this should be okay since we've checked it b4

                    String type;
                    if (rdBtnBook.isSelected())
                        type = "ΒΙΒΛΙΟ";
                    else if (rdBtnLogging.isSelected())
                        type = "ΠΡΑΚΤΙΚΑ ΣΥΝΕΔΡΙΩΝ";
                    else
                        type = "ΠΕΡΙΟΔΙΚΟ";

                    libManager.addBook(Helper.getScientificIdentifier(), title, writer, ISBN, date, type, freetext);
                }).start();
                dispose();
            }
        });

        pack();
    }

    boolean inputIsValid() {
        //do all user input validity checks here

        //checks if a radiobutton is selected
        boolean rdBtnIsChecked = rdBtnLogging.isSelected() ||
                rdBtnBook.isSelected() ||
                rdBtnMagazine.isSelected();
        // checks if the date is valid (<=4 digits allowed)
        boolean dateIsValid = txtFldDate.getText().matches("[0-9]+") && txtFldDate.getText().length() <= 4;
        // checks if ISBN is valid (13 dig, only digits, starts with 978 or 979
        boolean ISBNIsValid = txtFldISBN.getText().length() == 13 &&
                txtFldISBN.getText().matches("[0-9]+") &&
                (txtFldISBN.getText().startsWith("978") || txtFldISBN.getText().startsWith("979"));

        String title = Helper.normalizeGreek(txtFldBookTitle.getText()).toUpperCase();
        boolean titleIsValid = Helper.isInEnglish(title) || Helper.isInGreek(title);

        String writer = Helper.normalizeGreek(txtFldWriterName.getText()).toUpperCase();
        boolean writerIsValid = Helper.isInEnglish(writer) || Helper.isInGreek(writer);
        boolean fieldIsValid = !txtPaneFreetext.getText().isEmpty() && !txtPaneFreetext.getText().contains("\n");

        return rdBtnIsChecked && dateIsValid && ISBNIsValid && titleIsValid && writerIsValid && fieldIsValid;
    }

    boolean userIsDone() {
        if (inputIsValid()) {
            //show prompt to confirm
            //0 is OK
            //1 is NO
            //2 is cancel
            int ans = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this book?");

            //if ans is 0 (ok) return true
            return ans == 0;

        } else {
            //show warning that it cannot finish
            JOptionPane.showMessageDialog(this, "Data is invalid -- please check again.");
        }

        return false;
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
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 2, new Insets(5, 5, 5, 5), -1, -1));
        contentPane.putClientProperty("html.disable", Boolean.FALSE);
        txtFldBookTitle = new JTextField();
        contentPane.add(txtFldBookTitle, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldWriterName = new JTextField();
        contentPane.add(txtFldWriterName, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldISBN = new JTextField();
        contentPane.add(txtFldISBN, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldDate = new JTextField();
        contentPane.add(txtFldDate, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rdBtnMagazine = new JRadioButton();
        rdBtnMagazine.setText("ΠΕΡΙΟΔΙΚΟ");
        panel1.add(rdBtnMagazine, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rdBtnLogging = new JRadioButton();
        rdBtnLogging.setText("ΠΡΑΚΤΙΚΑ ΣΥΝΕΔΡΙΩΝ");
        panel1.add(rdBtnLogging, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rdBtnBook = new JRadioButton();
        rdBtnBook.setText("ΒΙΒΛΙΟ");
        panel1.add(rdBtnBook, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFldExpl = new JTextField();
        txtFldExpl.setHorizontalAlignment(0);
        txtFldExpl.setText("Book Title");
        contentPane.add(txtFldExpl, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldExpl2 = new JTextField();
        txtFldExpl2.setHorizontalAlignment(0);
        txtFldExpl2.setText("Writer Name");
        contentPane.add(txtFldExpl2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldExpl3 = new JTextField();
        txtFldExpl3.setHorizontalAlignment(0);
        txtFldExpl3.setText("ISBN");
        contentPane.add(txtFldExpl3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldExpl4 = new JTextField();
        txtFldExpl4.setHorizontalAlignment(0);
        txtFldExpl4.setText("Release Date (YYYY)");
        contentPane.add(txtFldExpl4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldExpl5 = new JTextField();
        txtFldExpl5.setHorizontalAlignment(0);
        txtFldExpl5.setText("Type");
        contentPane.add(txtFldExpl5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFldExpl6 = new JTextField();
        txtFldExpl6.setHorizontalAlignment(0);
        txtFldExpl6.setText("Scientific Field");
        contentPane.add(txtFldExpl6, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(31);
        contentPane.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtPaneFreetext = new JTextArea();
        scrollPane1.setViewportView(txtPaneFreetext);
        btnDone = new JButton();
        btnDone.setText("Finish");
        btnDone.setToolTipText("Finish adding book.");
        contentPane.add(btnDone, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdBtnMagazine);
        buttonGroup.add(rdBtnBook);
        buttonGroup.add(rdBtnLogging);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
