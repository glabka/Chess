package Game;

import pieces.Color;
import pieces.Piece;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author glabka
 */
public class MoveTracker {

    // variables containing information about last move
    private int verFrom;
    private int horFrom;
    private int verTo;
    private int horTo;
    private Piece piece;

    // variables needed for Castle evaluation
    private boolean whiteKingMoved = false;
    private boolean whiteKingsideRookMoved = false;
    private boolean whiteQueensideRookMoved = false;
    private boolean blackKingMoved = false;
    private boolean blackKingsideRookMoved = false;
    private boolean blackQueensideRookMoved = false;

    public MoveTracker() {

    }

    public MoveTracker(MoveTracker mt) {
        verFrom = mt.getVerFrom();
        horFrom = mt.getHorFrom();
        verTo = mt.getVerTo();
        horTo = mt.getHorTo();
        piece = mt.getPiece();

        whiteKingMoved = mt.didKingMove(Color.WHITE);
        whiteKingsideRookMoved = mt.didKingsideRookMove(Color.WHITE);
        whiteQueensideRookMoved = mt.didQueensideRookMove(Color.WHITE);
        blackKingMoved = mt.didKingMove(Color.BLACK);
        blackKingsideRookMoved = mt.didKingsideRookMove(Color.BLACK);
        blackQueensideRookMoved = mt.didQueensideRookMove(Color.BLACK);
    }

    public void storeMove(Piece piece, int verFrom, int horFrom, int verTo, int horTo) {
        this.piece = piece;
        this.verFrom = verFrom;
        this.horFrom = horFrom;
        this.verTo = verTo;
        this.horTo = horTo;
    }

    public void storeKingMoved(Color color) {
        if (color == Color.WHITE) {
            this.whiteKingMoved = true;
        } else {
            this.blackKingMoved = true;
        }
    }

    public void storeKingsideRookMoved(Color color) {
        if (color == Color.WHITE) {
            this.whiteKingsideRookMoved = true;
        } else {
            this.blackKingsideRookMoved = true;
        }
    }

    public void storeQueensideRookMoved(Color color) {
        if (color == Color.WHITE) {
            this.whiteQueensideRookMoved = true;
        } else {
            this.blackQueensideRookMoved = true;
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

    public boolean didKingMove(Color color) {
        if (color == Color.WHITE) {
            return whiteKingMoved;
        } else {
            return blackKingMoved;
        }
    }

    public boolean didKingsideRookMove(Color color) {
        if (color == Color.WHITE) {
            return whiteKingsideRookMoved;
        } else {
            return blackKingsideRookMoved;
        }
    }

    public boolean didQueensideRookMove(Color color) {
        if (color == Color.WHITE) {
            return whiteQueensideRookMoved;
        } else {
            return blackQueensideRookMoved;
        }
    }

}
