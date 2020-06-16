package com.gerelef;

import com.gerelef.gui.IntroductionGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        /*magic lines that set the look and feel of the ui from the deprecated
         * swing feel, to the current system feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException  |
                IllegalAccessException  |
                UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        IntroductionGUI gui = new IntroductionGUI();
        gui.setVisible(true);
    }
}
