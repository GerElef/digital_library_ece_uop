package com.gerelef.gui.insert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertDialog extends JDialog {
    private JPanel contentPane;
    private JButton btnLiterary;
    private JButton btnScientific;
    private JTextField selectBookTypeTextField;

    public InsertDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(400, 400));

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        selectBookTypeTextField.setOpaque(false);
        selectBookTypeTextField.setEditable(false);

        btnLiterary.addActionListener(e -> {
            //start literary gui
            dispose();
        });

        btnScientific.addActionListener(e -> {
            InsertScientificBookDialog scientificBookDialog = new InsertScientificBookDialog();
            scientificBookDialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            scientificBookDialog.setVisible(true);
            dispose();
        });

        pack();
    }
}