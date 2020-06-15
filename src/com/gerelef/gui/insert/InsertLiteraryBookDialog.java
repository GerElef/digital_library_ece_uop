package com.gerelef.gui.insert;

import com.gerelef.model.Helper;
import com.gerelef.model.IOLibManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertLiteraryBookDialog extends JDialog {
    private JPanel contentPane;
    private JTextField txtFldBookTitle;
    private JTextField txtFldWriterName;
    private JTextField txtFldISBN;
    private JTextField txtFldDate;
    private JRadioButton rdBtnFiction;
    private JRadioButton rdBtnNovel;
    private JRadioButton rdBtnNarration;
    private JRadioButton rdBtnPoetry;
    private JTextField txtFldExpl;
    private JTextField txtFldExpl2;
    private JTextField txtFldExpl3;
    private JTextField txtFldExpl4;
    private JTextField txtFldExpl5;
    private JButton btnDone;

    public InsertLiteraryBookDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(600, 250));
        setTitle("Insert Literary Book");

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

        txtFldExpl.setOpaque(true);
        txtFldExpl2.setOpaque(true);
        txtFldExpl3.setOpaque(true);
        txtFldExpl4.setOpaque(true);
        txtFldExpl5.setOpaque(true);

        btnDone.addActionListener(e -> {
            if (userIsDone()) {
                new Thread(() -> {
                    IOLibManager libManager = IOLibManager.getInstance();
                    String title = Helper.normalizeGreek(txtFldBookTitle.getText()).toUpperCase().trim();
                    String writer = Helper.normalizeGreek(txtFldWriterName.getText()).toUpperCase().trim();
                    long ISBN = Long.parseLong(txtFldISBN.getText());
                    int date = Integer.parseInt(txtFldDate.getText());

                    String type;
                    if (rdBtnFiction.isSelected())
                        type = "ΜΥΘΙΣΤΟΡΗΜΑ";
                    else if (rdBtnNarration.isSelected())
                        type = "ΔΙΗΓΗΜΑ";
                    else if (rdBtnNovel.isSelected())
                        type = "ΝΟΥΒΕΛΑ";
                    else
                        type = "ΠΟΙΗΣΗ";

                    libManager.addBook(Helper.getLiteratureIdentifier(), title, writer, ISBN, date, type);
                }).start();
                dispose();
            }
        });

        pack();
    }

    boolean inputIsValid() {
        //do all user input validity checks here
        boolean rdBtnIsChecked = rdBtnFiction.isSelected() ||
                rdBtnNarration.isSelected() ||
                rdBtnNovel.isSelected() ||
                rdBtnPoetry.isSelected();

        boolean dateIsValid = txtFldDate.getText().matches("[0-9]+") && txtFldDate.getText().length() <= 4;
        boolean ISBNIsValid = txtFldISBN.getText().length() == 13 &&
                txtFldISBN.getText().matches("[0-9]+") &&
                (txtFldISBN.getText().substring(0, 3).equals("978") || txtFldISBN.getText().substring(0, 3).equals("979"));

        String title = Helper.normalizeGreek(txtFldBookTitle.getText()).toUpperCase();
        boolean titleIsValid = Helper.isInEnglish(title) || Helper.isInGreek(title);

        String writer = Helper.normalizeGreek(txtFldWriterName.getText()).toUpperCase();
        boolean writerIsValid = Helper.isInEnglish(writer) || Helper.isInGreek(writer);


        return rdBtnIsChecked && dateIsValid && ISBNIsValid && titleIsValid && writerIsValid;
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
