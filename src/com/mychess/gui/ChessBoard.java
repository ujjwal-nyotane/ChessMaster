package com.mychess.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;

import com.mychess.board.Board;

import com.mychess.pieces.Piece;
import com.mychess.pieces.PieceColor;

public class ChessBoard extends JPanel {
    private Board board;
    private ChessSquare[][] squares = new ChessSquare[8][8];
    private int selectedRow = -1;
    private int selectedCol = -1;
    private PieceColor currentTurn = PieceColor.WHITE;

    public ChessBoard() {
        this.board = new Board();
        setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessSquare square = new ChessSquare( i, j);

                if ((i + j) % 2 == 0) {
                    square.setBackground(new Color(240, 217, 181));
                } else {
                    square.setBackground(new Color(181, 136, 99));
                }
                
                squares[i][j] = square;

                add(squares[i][j]);

                final int row = i;
                final int col = j;
                square.addActionListener(e -> handleSquareClick(row, col));
            }
        }
        setPreferredSize(new Dimension(800, 800));
        updateBoard();

    }

    void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.getPiece(i, j);
                squares[i][j].setIcon(ImageLoader.getPiece(piece));
            }
        }
    }

    void handleSquareClick(int r, int c) {
        if(selectedRow==-1){
            Piece piece = board.getPiece(r,c);
            if(piece!=null && piece.getcolor() == currentTurn){
                selectedRow =r ;
                selectedCol = c;
                squares[r][c].setSelectedSquare(true);
            }
        }
        else{
            if(selectedRow==r && selectedCol==c){
                squares[r][c].setSelectedSquare(false);
                selectedCol=-1;
                selectedRow=-1;
            }
            else if(board.getPiece(r, c) !=null && board.getPiece(r, c).getcolor()==currentTurn){
                squares[selectedRow][selectedCol].setSelectedSquare(false);
                selectedRow=r;
                selectedCol=c;
                squares[r][c].setSelectedSquare(true);
            }
            else if(board.isValidMove(selectedRow,selectedCol,r,c)){
                squares[selectedRow][selectedCol].setSelectedSquare(false);
                board.movePiece(selectedRow, selectedCol, r, c);
                currentTurn = currentTurn.opposite();
                selectedRow=-1;
                selectedCol=-1;
                updateBoard();
                }
            
        }
    }


}
