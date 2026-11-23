package com.mychess;
import javax.swing.SwingUtilities;

import com.mychess.gui.ChessFrame;
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->ChessFrame.initFrame());
    }
}