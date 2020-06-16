package com.gerelef.gui.insert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* User picks what kind of book to add, literature or scientific */
public class InsertDialog extends JDialog {
    private JPanel contentPane;
    private JButton btnLiterary;
    private JButton btnScientific;
    private JTextField selectBookTypeTextField;

    public InsertDialog() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(400, 400));
        setTitle("Type of book to insert.");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        selectBookTypeTextField.setOpaque(false);
        selectBookTypeTextField.setEditable(false);

        btnLiterary.addActionListener(e -> {
            InsertLiteraryBookDialog literaryBookDialog = new InsertLiteraryBookDialog();
            literaryBookDialog.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            literaryBookDialog.setVisible(true);
            dispose();
        });

        btnScientific.addActionListener(e -> {
            InsertScientificBookDialog scientificBookDialog = new InsertScientificBookDialog();
            scientificBookDialog.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            scientificBookDialog.setVisible(true);
            dispose();
        });

        pack();
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
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(10, 10, 10, 10), -1, -1));
        btnScientific = new JButton();
        btnScientific.setText("Scientific Book");
        contentPane.add(btnScientific, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnLiterary = new JButton();
        btnLiterary.setText("Literary Book");
        contentPane.add(btnLiterary, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        selectBookTypeTextField = new JTextField();
        selectBookTypeTextField.setHorizontalAlignment(0);
        selectBookTypeTextField.setText("Select book type");
        contentPane.add(selectBookTypeTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
