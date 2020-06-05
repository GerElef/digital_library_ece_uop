package com.gerelef.gui.insert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertLiteraryBookDialog extends JDialog {
    private JPanel contentPane;
    private JButton btnDone;

    public InsertLiteraryBookDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(600, 600));

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

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
