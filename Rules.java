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
    public static boolean isMoveLegal(Player p, Board b, MovesContainer con, int verFrom, int horFrom, int verTo, int horTo) {
        // Both PieceEnum and Color can be null if there's no piece on the location
        PieceEnum movingPieceType = b.getPieceType(verFrom, horFrom);
        Color movingPieceColor = b.getPieceColor(verFrom, horFrom);

        PieceEnum standingPieceType = b.getPieceType(verTo, horTo);
        Color standingPieceColor = b.getPieceColor(verTo, horTo);

        if (movingPieceType == null) {
            return false;
        }

        if (p.getColor() != movingPieceColor) {
            return false;
        }

        if (movingPieceColor == standingPieceColor) {
            return false;
        }

        if (movingPieceType == PieceEnum.KING) {
            return isKingsMoveLegal(b, con, verFrom, horFrom, verTo, horTo);
        } else if (movingPieceType == PieceEnum.QUEEN) {
            return isQueensMoveLegal(b, verFrom, horFrom, verTo, horTo);
        } else if (movingPieceType == PieceEnum.BISHOP) {
            return isBishopsMoveLegal(b, verFrom, horFrom, verTo, horTo);
        } else if (movingPieceType == PieceEnum.KNIGHT) {
            return isKnightsMoveLegal(b, verFrom, horFrom, verTo, horTo);
        } else if (movingPieceType == PieceEnum.ROOK) {
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
        Color movingPieceColor = b.getPieceColor(verFrom, horFrom);
        // Color of stanignPiece can be null if there's no piece on the location
        Color standingPieceColor = b.getPieceColor(verTo, horTo);
        if (movingPieceColor == Color.BLACK) {
            // first move
            // moving forward
//            if(verFrom == 1)

            // normal capture
            // en passant
        }

    }

    private static boolean isPathBetweenFree(Board b, int verI, int horI, int verJ, int horJ) {
        if (verI != verJ && horI != horJ && !onSameDiagonal(verI, horI, verJ, horJ)) {
            throw new IllegalArgumentException("Entered positions are not on the same line (vertical, horizontal or diagonal).");
        }
        if (verI == verJ) {
            return isVerticalPathFree(b, verI, horI, verJ, horJ);
        } else if (horI == horJ) {
            return isHorizontalPathFree(b, verI, horI, verJ, horJ);
        } else { // diagonal
            return isDiagonalPathFree(b, verI, horI, verJ, horJ);
        }
    }

    private static boolean isVerticalPathFree(Board b, int verI, int horI, int verJ, int horJ) {
        int numOfPositionBetween = horI - horJ - 1;
        for (int i = 0; i < numOfPositionBetween; i++) {
            if (b.getPieceType(verI, horI + i + 1) != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean isHorizontalPathFree(Board b, int verI, int horI, int verJ, int horJ) {
        int numOfPositionBetween = verI - verJ - 1;
        for (int i = 0; i < numOfPositionBetween; i++) {
            if (b.getPieceType(verI + i + 1, horI) != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDiagonalPathFree(Board b, int verI, int horI, int verJ, int horJ) {
        int verLeft, verRight, horLeft, horRight;
        if (verI < verJ) {
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
        
        // upward diagonal
        if(horLeft < horRight){
            
        } else { // downward diagonal
            
        }
    }

    private static boolean onSameDiagonal(int verI, int horI, int verJ, int horJ) {
        if (Math.abs(verI - verJ) == Math.abs(horI - horJ)) {
            return true;
        } else {
            return false;
        }
    }

}
