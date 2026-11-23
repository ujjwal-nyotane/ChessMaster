package com.mychess.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import  java.awt.GridLayout;

import com.mychess.board.Board;

import com.mychess.pieces.Piece;

public class ChessBoard extends JPanel{
    private Board board;
    private JButton[][] squares = new JButton[8][8];
    
    public ChessBoard(){
        this.board = new Board();
        setLayout(new GridLayout(8,8));
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                JButton square = new JButton();

                if((i+j)%2==0){
                    square.setBackground(new Color(240,217,181));
                }
                else{
                    square.setBackground(new Color(181,136,99));
                }
                square.setOpaque(true);
                square.setBorderPainted(false);
                square.setFocusPainted(false);
                squares[i][j]=square;

                add(squares[i][j]);
            }
        }
        setPreferredSize(new Dimension(800,800));
        updateBoard();
        
    }
    void updateBoard(){
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                Piece piece = board.getPiece(i,j);
                squares[i][j].setIcon(ImageLoader.getPiece(piece));
            }
        }
    }
    
    
}
