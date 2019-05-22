/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class Rules {

//    /**
//     * 
//     * @param b Board 
//     * @param p Player
//     * @param verFrom vertical index from where the piece should be moved
//     * @param horFrom horizontal index from where the piece should be moved
//     * @param verTo vertical index to position where the piece should be moved
//     * @param horTo horizontal index to position where the piece should be moved
//     * @return true if the move is possible
//     */
//    public static boolean move(Board b, Player p, int verFrom, int horFrom, int verTo, int horTo){
//        if()
//    }
    public void testing(Player p, Board b, MovesContainer con, int verFrom, int horFrom, int verTo, int horTo) {

    }

    public void testing(Board b) {
//        System.out.println("true: " + isVerticalPathFree(b, 6, 2, 1, 2));
//        System.out.println("false: " + isVerticalPathFree(b, 1, 2, 7, 2));
//        System.out.println("");
//        System.out.println("true: " + isHorizontalPathFree(b, 1, 2, 1, 6));
//        System.out.println("false: " + isHorizontalPathFree(b, 1, 2, 1, 7));

//        System.out.println("true: " + isDiagonalPathFree(b, 4, 5, 1, 2));
//        System.out.println("false: " + isDiagonalPathFree(b, 5, 6, 1, 2));
//        System.out.println("true: " + isDiagonalPathFree(b, 6, 2, 3, 5));
//        System.out.println("true: " + isDiagonalPathFree(b, 6, 2, 3, 5));
//        System.out.println("false: " + isDiagonalPathFree(b, 1, 7, 6, 2));
//        System.out.println("true: " + isVerticalPathFree(b, 3, 5, 4, 5));
        System.out.println("true: " + isPathBetweenFree(b, 6, 2, 3, 5));
        System.out.println("false: " + isPathBetweenFree(b, 1, 7, 6, 2));
        System.out.println("");
        System.out.println("true: " + isPathBetweenFree(b, 6, 2, 1, 2));
        System.out.println("false: " + isPathBetweenFree(b, 1, 2, 7, 2));
        System.out.println("");
        System.out.println("true: " + isPathBetweenFree(b, 1, 2, 1, 6));
        System.out.println("false: " + isPathBetweenFree(b, 1, 2, 1, 7));

    }

    public static boolean isMoveLegal(Player p, Board b, MovesContainer con, int verFrom, int horFrom, int verTo, int horTo) {
        // Piece can be null if there's no piece on the location
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        Piece standingPiece = b.getPiece(verTo, horTo);

        if (movingPiece == null) {
            return false;
        }

        if (p.getColor() != movingPiece.getColor()) {
            return false;
        }

        if (movingPiece.getColor() == standingPiece.getColor()) {
            return false;
        }

        if (movingPiece instanceof King) {
            return isKingsMoveLegal(b, con, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Queen) {
            return isQueensMoveLegal(b, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Bishop) {
            return isBishopsMoveLegal(b, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Knight) {
            return isKnightsMoveLegal(b, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Rook) {
            return isRooksMoveLegal(b, verFrom, horFrom, verTo, horTo);
        } else { // pawn
            return isPawnsMoveLegal(b, con, verFrom, horFrom, verTo, horTo);
        }

    }

    private static boolean isKingsMoveLegal(Board b, MovesContainer con, int verFrom, int horFrom, int verTo, int horTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean isQueensMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean isBishopsMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean isKnightsMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean isRooksMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean isPawnsMoveLegal(Board b, MovesContainer con, int verFrom, int horFrom, int verTo, int horTo) {
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        // Piece can be null if there's no piece on the location
        Piece standingPiece = b.getPiece(verTo, horTo);
        if (movingPiece.getColor() == Color.BLACK) {
            // first move - two steps
            if (verFrom == 1 && verTo == 3 && verFrom == verTo && standingPiece == null && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
                return true;
            } // moving forward
            else if (verFrom + 1 == verTo && horFrom == horTo ){
                
            }
                // normal capture
            // en passant
            {
                
            }
        }

    }

    private static boolean isPathBetweenFree(Board b, int verI, int horI, int verJ, int horJ) {
        if (verI != verJ && horI != horJ && !onSameDiagonal(verI, horI, verJ, horJ)) {
            throw new IllegalArgumentException("Entered positions are not on the same line (vertical, horizontal or diagonal).");
        }
        if (verI == verJ) {
            return isHorizontalPathFree(b, verI, horI, verJ, horJ);
        } else if (horI == horJ) {
            return isVerticalPathFree(b, verI, horI, verJ, horJ);
        } else { // diagonal
            return isDiagonalPathFree(b, verI, horI, verJ, horJ);
        }
    }

    private static boolean isHorizontalPathFree(Board b, int verI, int horI, int verJ, int horJ) {
        int numOfPositionBetween = Math.abs(horI - horJ) - 1;
        int verLeft, horLeft, verRight, horRight;
        if (horI < horJ) {
            verLeft = verI;
            horLeft = horI;
            verRight = verJ;
            horRight = horJ;
        } else {
            verLeft = verJ;
            horLeft = horJ;
            verRight = verI;
            horRight = horI;
        }
        for (int i = 0; i < numOfPositionBetween; i++) {
            System.out.print(verLeft + "," + (horLeft + i + 1) + "; "); // debug
            if (b.getPiece(verLeft, horLeft + i + 1) != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean isVerticalPathFree(Board b, int verI, int horI, int verJ, int horJ) {
        int numOfPositionBetween = Math.abs(verI - verJ) - 1;
        int verDown, horDown, verUp, horUp;
        if (verI < verJ) {
            verDown = verI;
            horDown = horI;
            verUp = verJ;
            horUp = horJ;
        } else {
            verDown = verJ;
            horDown = horJ;
            verUp = verI;
            horUp = horI;
        }

        for (int i = 0; i < numOfPositionBetween; i++) {
            System.out.print((verDown + i + 1) + "," + horDown + "; "); // debug
            if (b.getPiece(verDown + i + 1, horDown) != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDiagonalPathFree(Board b, int verI, int horI, int verJ, int horJ) {
        int verLeft, verRight, horLeft, horRight;
        if (horI < horJ) {
            verLeft = verI;
            horLeft = horI;
            verRight = verJ;
            horRight = horJ;
        } else {
            verLeft = verJ;
            horLeft = horJ;
            verRight = verI;
            horRight = horI;
        }

        int numOfPositionsBetween = horRight - horLeft - 1;
        int d;
        // downward diagonal
        if (verLeft < verRight) {
            d = 1;
        } else { // upward diagonal
            d = -1;
        }

        for (int i = 0; i < numOfPositionsBetween; i++) {
            System.out.print((verLeft + i * d + d) + "," + (horLeft + i + 1) + "; "); // debug
            if (b.getPiece(verLeft + i * d + d, horLeft + i + 1) != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean onSameDiagonal(int verI, int horI, int verJ, int horJ) {
        if (Math.abs(verI - verJ) == Math.abs(horI - horJ)) {
            return true;
        } else {
            return false;
        }
    }

}
