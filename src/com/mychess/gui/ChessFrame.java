package com.mychess.gui;

import javax.swing.JFrame;

public class ChessFrame {

    public static void initFrame() {
        JFrame jframe = new JFrame("Chess 1.0.1 beta");

        jframe.add(new ChessBoard());
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.pack();
        jframe.setLocationRelativeTo(null);

        jframe.setVisible(true);
    }

}