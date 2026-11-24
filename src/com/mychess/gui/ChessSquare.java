package com.mychess.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class ChessSquare extends JButton{
    private final int row;
    private final int col;
    private boolean selectedSquare=false;
    public ChessSquare(int row,int col){
        this.row=row;
        this.col=col;
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
    }
    public void setSelectedSquare(boolean selectedSquare){
        this.selectedSquare =selectedSquare;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        if(selectedSquare){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(105, 146, 62, 100));
            g2.fillRect(0,0,getWidth(),getHeight());
            g2.dispose();
        }
    }
}
