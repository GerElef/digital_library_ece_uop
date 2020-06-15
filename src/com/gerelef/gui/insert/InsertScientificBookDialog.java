package com.gerelef.gui.insert;

import com.gerelef.model.Helper;
import com.gerelef.model.IOLibManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
                    IOLibManager libManager = IOLibManager.getInstance();
                    String title = Helper.normalizeGreek(txtFldBookTitle.getText()).toUpperCase().trim();
                    String writer = Helper.normalizeGreek(txtFldWriterName.getText()).toUpperCase().trim();
                    long ISBN = Long.parseLong(txtFldISBN.getText());
                    int date = Integer.parseInt(txtFldDate.getText());
                    String freetext = txtPaneFreetext.getText();

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
        boolean rdBtnIsChecked = rdBtnLogging.isSelected() ||
                rdBtnBook.isSelected() ||
                rdBtnMagazine.isSelected();

        boolean dateIsValid = txtFldDate.getText().matches("[0-9]+") && txtFldDate.getText().length() <= 4;
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

}
