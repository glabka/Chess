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
    private PieceEnum pieceType;
    private Color pieceColor;

    // variables needed for Castle evaluation
    private boolean whiteKingMoved = false;
    private boolean whiteRook1Moved = false; // the rook on the left side
    private boolean whiteRook2Moved = false;
    private boolean blackKingMoved = false;
    private boolean blackRook1Moved = false; // the rook on the left side
    private boolean blackRook2Moved = false;

    public void storeMove(PieceEnum pieceType, Color pieceColor, int verFrom, int horFrom, int verTo, int horTo) {
        this.pieceType = pieceType;
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

    public PieceEnum getPieceType() {
        return pieceType;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public boolean isWhiteKingMoved() {
        return whiteKingMoved;
    }

    public boolean isWhiteRook1Moved() {
        return whiteRook1Moved;
    }

    public boolean isWhiteRook2Moved() {
        return whiteRook2Moved;
    }

    public boolean isBlackKingMoved() {
        return blackKingMoved;
    }

    public boolean isBlackRook1Moved() {
        return blackRook1Moved;
    }

    public boolean isBlackRook2Moved() {
        return blackRook2Moved;
    }

}
