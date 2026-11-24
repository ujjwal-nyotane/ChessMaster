package com.mychess.board;

import com.mychess.pieces.Piece;
import com.mychess.pieces.PieceColor;
import com.mychess.pieces.PieceType;

public class Board {
    private Piece[][] grid = new Piece[8][8];

    public Board() {
        setupInitialPosition();
    }

    public void setupInitialPosition() {
        grid[0][0] = new Piece(PieceColor.BLACK, PieceType.ROOK);
        grid[0][1] = new Piece(PieceColor.BLACK, PieceType.KNIGHT);
        grid[0][2] = new Piece(PieceColor.BLACK, PieceType.BISHOP);
        grid[0][3] = new Piece(PieceColor.BLACK, PieceType.QUEEN);
        grid[0][4] = new Piece(PieceColor.BLACK, PieceType.KING);
        grid[0][5] = new Piece(PieceColor.BLACK, PieceType.BISHOP);
        grid[0][6] = new Piece(PieceColor.BLACK, PieceType.KNIGHT);
        grid[0][7] = new Piece(PieceColor.BLACK, PieceType.ROOK);

        for (int i = 0; i < 8; i++) {
            grid[1][i] = new Piece(PieceColor.BLACK, PieceType.PAWN);
        }
        for (int i = 0; i < 8; i++) {
            grid[6][i] = new Piece(PieceColor.WHITE, PieceType.PAWN);
        }
        grid[7][0] = new Piece(PieceColor.WHITE, PieceType.ROOK);
        grid[7][1] = new Piece(PieceColor.WHITE, PieceType.KNIGHT);
        grid[7][2] = new Piece(PieceColor.WHITE, PieceType.BISHOP);
        grid[7][3] = new Piece(PieceColor.WHITE, PieceType.QUEEN);
        grid[7][4] = new Piece(PieceColor.WHITE, PieceType.KING);
        grid[7][5] = new Piece(PieceColor.WHITE, PieceType.BISHOP);
        grid[7][6] = new Piece(PieceColor.WHITE, PieceType.KNIGHT);
        grid[7][7] = new Piece(PieceColor.WHITE, PieceType.ROOK);
    }

    public Piece getPiece(int row, int col) {
        if (row < 8 && row >= 0 && col < 8 && col >= 0) {
            return grid[row][col];
        }

        return null;
    }

    public void setPiece(int row, int col, Piece piece) {
        if (row < 8 && row >= 0 && col < 8 && col >= 0) {
            grid[row][col] = piece;
        }
    }

    public void movePiece(int fromrow, int fromcol, int torow, int tocol) {
        Piece piece = getPiece(fromrow, fromcol);
        if (piece != null) {
            setPiece(torow, tocol, piece);
            setPiece(fromrow, fromcol, null);
            piece.markMoved();
        }

    }

    public boolean isValidMove(int fromrow, int fromcol, int torow, int tocol) {
        Piece piece = getPiece(fromrow, fromcol);
        Piece targetPiece = getPiece(torow, tocol);
        if (targetPiece != null && piece.getcolor() == targetPiece.getcolor()) {
            return false;
        }

        switch (piece.getType()) {
            case KNIGHT:
                return isValidKnightMove(fromrow, fromcol, torow, tocol);
            case PAWN:
                return isValidPawnMove(fromrow, fromcol, torow, tocol);
            case ROOK:
                return isValidRookMove(fromrow, fromcol, torow, tocol);
            case BISHOP:
                return isValidBishopMove(fromrow, fromcol, torow, tocol);
            case QUEEN:
                return isValidQueenMove(fromrow, fromcol, torow, tocol);
            case KING:
                return isValidKingMove(fromrow, fromcol, torow, tocol);
            case null, default:
                return false;
        }

    }

    boolean isValidKnightMove(int fromrow, int fromcol, int torow, int tocol) {
        int rowdiff = Math.abs(fromrow - torow);
        int coldiff = Math.abs(fromcol - tocol);
        if ((rowdiff == 1 && coldiff == 2) || (rowdiff == 2 && coldiff == 1))
            return true;
        return false;
    }

    boolean isPathClear(int fromrow,int fromcol, int torow,int tocol){
        int rowdir= Integer.compare(torow, fromrow);
        int coldir = Integer.compare(tocol, fromcol);
        System.out.println(fromrow+" "+fromcol+" "+torow+" "+tocol);
        System.out.println(rowdir+" "+coldir);
        int currRow = fromrow+rowdir;
        int currCol =fromcol+coldir;

        while(currRow!=torow|| currCol!=tocol){
            if(getPiece(currRow, currCol)!=null) return false;
            currRow +=rowdir;
            currCol +=coldir;
        }

        return true;

    }
    boolean isValidRookMove(int fromrow,int fromcol, int torow,int tocol){
        if((fromrow==torow || fromcol==tocol)&& isPathClear(fromrow, fromcol, torow, tocol)){
            return true;
        }
        return false;
    }
    boolean isValidBishopMove(int fromrow,int fromcol, int torow,int tocol){
        if(Math.abs(torow-fromrow)==Math.abs(tocol-fromcol) && isPathClear(fromrow, fromcol, torow, tocol)){
            return true;
        }
        return false;
    }
    boolean isValidQueenMove(int fromrow,int fromcol, int torow,int tocol){
        if(isValidBishopMove(fromrow, fromcol, torow, tocol) || isValidRookMove(fromrow, fromcol, torow, tocol)){
            return true;
        }
        return false;
    }
    boolean isValidKingMove(int fromrow,int fromcol, int torow,int tocol){
        if(Math.abs(torow-fromrow)<=1 && Math.abs(tocol-fromcol)<=1){
            return true;
        }
        return false;
    }
    boolean isValidPawnMove(int fromrow,int fromcol, int torow,int tocol){
        Piece piece = getPiece(fromrow, fromcol);
        int direction = piece.getcolor()==PieceColor.WHITE?-1:1;
        if(tocol==fromcol && torow == fromrow+direction && getPiece(torow, tocol)==null){
            return true;
        }
        if(!piece.hasMoved() && tocol==fromcol && torow == fromrow+2*direction && getPiece(torow, tocol)==null){
            return true;
        }
        if(Math.abs(tocol-fromcol)==1 && torow == fromrow+direction && getPiece(torow, tocol)!=null && getPiece(torow, tocol).getcolor()!=piece.getcolor()){
            return true;
        }

        return false;
    }


}
