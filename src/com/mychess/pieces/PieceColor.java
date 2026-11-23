package com.mychess.pieces;

public enum PieceColor {
    BLACK,
    WHITE;

    public PieceColor opposite(){
        switch(this){
            case BLACK:
                return WHITE;
            case WHITE:
                return BLACK;
            case null,default:
                return WHITE;
        }
    }

}

