package com.mychess.pieces;

public class Piece {
    private PieceColor color;
    private PieceType type;
    private boolean hasMoved;

    public Piece(PieceColor color,PieceType type){
        this.color=color;
        this.type=type;
        hasMoved=false;
    }
    public PieceColor getcolor(){
        return this.color;
    }
    public PieceType getType(){
        return this.type;
    }
    public boolean hasMoved(){
        return hasMoved;
    }
    public void markMoved(){
        hasMoved=true;
    }
}
