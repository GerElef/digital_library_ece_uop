package com.gerelef.gui.insert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertLiteraryBookDialog extends JDialog {
    private JPanel contentPane;
    private JTextField txtFldBookTitle;
    private JTextField txtFieldWriterName;
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
            if (userIsDone()){
                //add to db and dispose
                dispose();
            }
        });

        pack();
    }

    boolean inputIsValid(){
        boolean dataIsValid = true;

        //do all user input validity checks here

        return dataIsValid;
    }

    boolean userIsDone(){
        if (inputIsValid()){
            //show prompt to confirm
            //0 is OK
            //1 is NO
            //2 is cancel
            int ans = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this book?");

            //if ans is 0 (ok) return true
            return ans == 0;

        }else{
            //show warning that it cannot finish
            JOptionPane.showMessageDialog(this, "Data is invalid -- please check again.");
        }

        return false;
    }
}
