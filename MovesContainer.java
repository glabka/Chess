/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class MovesContainer {

    // variables containing information about last move
    private int verFrom;
    private int horFrom;
    private int verTo;
    private int horTo;
    private Piece piece;
    private Color pieceColor;

    // variables needed for Castle evaluation
    private boolean whiteKingMoved = false;
    private boolean whiteRook1Moved = false; // the rook on the left side
    private boolean whiteRook2Moved = false;
    private boolean blackKingMoved = false;
    private boolean blackRook1Moved = false; // the rook on the left side
    private boolean blackRook2Moved = false;
    
    public void storeMove(Piece piece, int verFrom, int horFrom, int verTo, int horTo) {
        this.piece = piece;
        this.pieceColor = pieceColor;
        this.verFrom = verFrom;
        this.horFrom = horFrom;
        this.verTo = verTo;
        this.horTo = horTo;
    }

    public void storeKingMove(Color color) {
        if (color == Color.WHITE) {
            this.whiteKingMoved = true;
        } else {
            this.blackKingMoved = true;
        }
    }

    public void storeRook1Move(Color color) {
        if (color == Color.WHITE) {
            this.whiteRook1Moved = true;
        } else {
            this.blackRook1Moved = true;
        }
    }

    public void storeRook2Move(Color color) {
        if (color == Color.WHITE) {
            this.whiteRook2Moved = true;
        } else {
            this.blackRook2Moved = true;
        }
    }

    public int getVerFrom() {
        return verFrom;
    }

    public int getHorFrom() {
        return horFrom;
    }

    public int getVerTo() {
        return verTo;
    }

    public int getHorTo() {
        return horTo;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean didWhiteKingMove() {
        return whiteKingMoved;
    }

    public boolean didWhiteRook1Move() {
        return whiteRook1Moved;
    }

    public boolean didWhiteRook2Move() {
        return whiteRook2Moved;
    }

    public boolean didBlackKingMove() {
        return blackKingMoved;
    }

    public boolean didBlackRook1Move() {
        return blackRook1Moved;
    }

    public boolean didBlackRook2Move() {
        return blackRook2Moved;
    }

}
