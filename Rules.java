
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
    public void testing(Player p, Board b, MoveTracker mt, int verFrom, int horFrom, int verTo, int horTo) {

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

    public static boolean move(Player p, Board b, MoveTracker mt, int iFrom, char cFrom, int iTo, char cTo) {
        int verFrom = Board.positionNumToIndex(iFrom);
        int horFrom = Board.positionCharToIndex(cFrom);
        int verTo = Board.positionNumToIndex(iTo);
        int horTo = Board.positionCharToIndex(cTo);
        return move(p, b, mt, verFrom, horFrom, verTo, horTo);
    }

    // MoveTracker is used to rember moves necessary for castling and en passant
    // whereas MoveType is used to contain information about move in form of MovesEnum
    public static boolean move(Player p, Board b, MoveTracker mt, int verFrom, int horFrom, int verTo, int horTo) {
        if (p == null) {
            throw new IllegalArgumentException("Player can't be null.");
        }
        if (mt == null) {
            throw new IllegalArgumentException("MoveKeeper can't be null.");
        }
        if (!Board.isIndexOnBoard(verFrom)) {
            throw new IllegalArgumentException("position verFrom = " + verFrom + " is out of bounds.");
        } else if (!Board.isIndexOnBoard(horFrom)) {
            throw new IllegalArgumentException("position horFrom = " + horFrom + " is out of bounds.");
        } else if (!Board.isIndexOnBoard(verTo)) {
            throw new IllegalArgumentException("position verTo = " + verTo + " is out of bounds.");
        } else if (!Board.isIndexOnBoard(horTo)) {
            throw new IllegalArgumentException("position horTo = " + horTo + " is out of bounds.");
        }

        MoveType mType = new MoveType();
        if (isMoveLegal(p.getColor(), b, mt, mType, verFrom, horFrom, verTo, horTo)) {
            Piece movingPiece = b.getPiece(verFrom, horFrom);

            // Tracking whether King or rooks moved (important for castling)
            if (movingPiece instanceof King) {
                mt.storeKingMoved(movingPiece.getColor());
            } else if (movingPiece instanceof Rook) {
                if (horFrom == 0) {
                    mt.storeQueensideRookMoved(movingPiece.getColor());
                } else {
                    mt.storeKingsideRookMoved(movingPiece.getColor());
                }
            }

            mt.storeMove(movingPiece, verFrom, horFrom, verTo, horTo);
            if (mType.getMoveType() == MovesEnum.STANDARD) {
                b.movePiece(verFrom, horFrom, verTo, horTo);
            } else if (mType.getMoveType() == MovesEnum.EN_PASSANT) {
                b.movePiece(verFrom, horFrom, verTo, horTo);
                b.deletePiece(verFrom, horTo);
            } else if (mType.getMoveType() == MovesEnum.WHITE_KINGSIDE_CASTLING) {
                b.movePiece(verFrom, horFrom, verTo, horTo); // moving king
                b.movePiece(7, 7, 7, 5); // moving rook
            } else if (mType.getMoveType() == MovesEnum.BLACK_KINGSIDE_CASTLING) {
                b.movePiece(verFrom, horFrom, verTo, horTo); // moving king
                b.movePiece(0, 7, 0, 5); // moving rook
            } else if (mType.getMoveType() == MovesEnum.WHITE_QUEENSIDE_CASTLING) {
                b.movePiece(verFrom, horFrom, verTo, horTo);
                b.movePiece(7, 0, 7, 3); // moving rook
            } else if (mType.getMoveType() == MovesEnum.BLACK_QUEENSIDE_CASTLING) {
                b.movePiece(verFrom, horFrom, verTo, horTo);
                b.movePiece(0, 0, 0, 3); // moving rook
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMoveLegal(Color playersColor, Board b, MoveTracker mt, MoveType mType, int verFrom, int horFrom, int verTo, int horTo) {
        // Piece can be null if there's no piece on the location
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        Piece standingPiece = b.getPiece(verTo, horTo);

        if (movingPiece == null) {
            return false;
        }

        if (playersColor != movingPiece.getColor()) {
            return false;
        }

        if (standingPiece != null && movingPiece.getColor() == standingPiece.getColor()) {
            return false;
        }

        if (movingPiece instanceof King) {
            return isKingsMoveLegal(b, mt, mType, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Queen) {
            mType.setMoveType(MovesEnum.STANDARD);
            return isQueensMoveLegal(b, mt, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Bishop) {
            mType.setMoveType(MovesEnum.STANDARD);
            return isBishopsMoveLegal(b, mt, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Knight) {
            mType.setMoveType(MovesEnum.STANDARD);
            return isKnightsMoveLegal(b, mt, verFrom, horFrom, verTo, horTo);
        } else if (movingPiece instanceof Rook) {
            mType.setMoveType(MovesEnum.STANDARD);
            return isRooksMoveLegal(b, mt, verFrom, horFrom, verTo, horTo);
        } else { // pawn
            return isPawnsMoveLegal(b, mt, mType, verFrom, horFrom, verTo, horTo);
        }

    }

    private static boolean isKingsMoveLegal(Board b, MoveTracker mt, MoveType mType, int verFrom, int horFrom, int verTo, int horTo) {
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        Color kingsColor = b.getPiece(verFrom, horFrom).getColor();

        if (!isPositionInCheck(b, kingsColor, mt, verTo, horTo)
                && (verFrom == verTo || verFrom + 1 == verTo || verFrom - 1 == verTo)
                && (horFrom == horTo || horFrom + 1 == horTo || horFrom - 1 == horTo)) {
            mType.setMoveType(MovesEnum.STANDARD);
            return true;
        } // white kingside castling
        else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor)
                && verFrom == 7 && horFrom == 4 && verTo == 7 && horTo == 6) {
            Piece rook = b.getPiece(7, 7);
            if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
                    && isPathBetweenFree(b, verFrom, horFrom, 7, 7)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom) && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 1)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 2)) {
                mType.setMoveType(MovesEnum.WHITE_KINGSIDE_CASTLING);
                return true;
            }
        } // black kingside castling
        else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor)
                && verFrom == 0 && horFrom == 4 && verTo == 0 && horTo == 6) {
            Piece rook = b.getPiece(0, 7);
            if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
                    && isPathBetweenFree(b, verFrom, horFrom, 0, 7)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom) && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 1)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 2)) {
                mType.setMoveType(MovesEnum.BLACK_KINGSIDE_CASTLING);
                return true;
            }
        } // white queenside castling
        else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor)
                && verFrom == 7 && horFrom == 4 && verTo == 7 && horTo == 2) {
            Piece rook = b.getPiece(7, 0);
            if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
                    && isPathBetweenFree(b, verFrom, horFrom, 7, 0)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom) && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 1)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 2)) {
                mType.setMoveType(MovesEnum.WHITE_QUEENSIDE_CASTLING);
                return true;
            }
        } // black queenside castling
        else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor)
                && verFrom == 0 && horFrom == 4 && verTo == 0 && horTo == 2) {
            Piece rook = b.getPiece(0, 0);
            if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
                    && isPathBetweenFree(b, verFrom, horFrom, 0, 0)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom) && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 1)
                    && !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 2)) {
                mType.setMoveType(MovesEnum.BLACK_QUEENSIDE_CASTLING);
                return true;
            }
        }
        return false;
    }

    private static boolean isQueensMoveLegal(Board b, MoveTracker mt, int verFrom, int horFrom, int verTo, int horTo) {
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        if (onSameDiagonal(verFrom, horFrom, verTo, horTo) && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
            return true;
        } else if (verFrom == verTo && horFrom != horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
            return true;
        } else if (verFrom != verTo && horFrom == horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isBishopsMoveLegal(Board b, MoveTracker mt, int verFrom, int horFrom, int verTo, int horTo) {
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        if (onSameDiagonal(verFrom, horFrom, verTo, horTo) && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isKnightsMoveLegal(Board b, MoveTracker mt, int verFrom, int horFrom, int verTo, int horTo) {
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        if ((verFrom + 2 == verTo || verFrom - 2 == verTo) && (horFrom + 1 == horTo || horFrom - 1 == horTo)) {
            return true;
        } else if ((verFrom + 1 == verTo || verFrom - 1 == verTo) && (horFrom + 2 == horTo || horFrom - 2 == horTo)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isRooksMoveLegal(Board b, MoveTracker mt, int verFrom, int horFrom, int verTo, int horTo) {
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        if (verFrom == verTo && horFrom != horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
            return true;
        } else if (verFrom != verTo && horFrom == horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isPawnsMoveLegal(Board b, MoveTracker mt, MoveType mType, int verFrom, int horFrom, int verTo, int horTo) {
        Piece movingPiece = b.getPiece(verFrom, horFrom);
        // Piece can be null if there's no piece on the location
        Piece standingPiece = b.getPiece(verTo, horTo);

        // debug
//        System.out.println(mt.getPiece() instanceof Pawn);
//        System.out.println("mt verFrom = " + mt.getVerFrom());
//        System.out.println("mt verTo = " + mt.getVerTo());
        // end of debug
        if (movingPiece.getColor() == Color.BLACK) {
            // first move - two steps
            if (verFrom == 1 && verTo == 3 && horFrom == horTo && standingPiece == null && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
                mType.setMoveType(MovesEnum.STANDARD);
                return true;
            } // moving forward
            else if (verFrom + 1 == verTo && horFrom == horTo && standingPiece == null) {
                mType.setMoveType(MovesEnum.STANDARD);
                return true;
            } // normal capture
            else if (verFrom + 1 == verTo && (horFrom + 1 == horTo || horFrom - 1 == horTo)
                    && standingPiece != null && standingPiece.getColor() != movingPiece.getColor()) {
                mType.setMoveType(MovesEnum.STANDARD);
                return true;
            } else if (verFrom == 4
                    && mt.getPiece() instanceof Pawn && mt.getVerFrom() == 6 && mt.getVerTo() == 4 // last move of Pawn was double step
                    && (horFrom - 1 == mt.getHorTo() || horFrom + 1 == mt.getHorTo()) && horTo == mt.getHorTo()) {
                mType.setMoveType(MovesEnum.EN_PASSANT);
                return true;
            } else {
                return false;
            }
        } else { // movingPiece == Color.WHITE
            // first move - two steps
            if (verFrom == 6 && verTo == 4 && horFrom == horTo && standingPiece == null && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
                mType.setMoveType(MovesEnum.STANDARD);
                return true;
            } // moving forward
            else if (verFrom - 1 == verTo && horFrom == horTo && standingPiece == null) {
                mType.setMoveType(MovesEnum.STANDARD);
                return true;
            } // normal capture
            else if (verFrom - 1 == verTo && (horFrom + 1 == horTo || horFrom - 1 == horTo)
                    && standingPiece != null && standingPiece.getColor() != movingPiece.getColor()) {
                mType.setMoveType(MovesEnum.STANDARD);
                return true;
            } else if (verFrom == 3
                    && mt.getPiece() instanceof Pawn && mt.getVerFrom() == 1 && mt.getVerTo() == 3 // last move of Pawn was double step
                    && (horFrom - 1 == mt.getHorTo() || horFrom + 1 == mt.getHorTo()) && horTo == mt.getHorTo()) {
                mType.setMoveType(MovesEnum.EN_PASSANT);
                return true;
            } else {
                return false;
            }
        }

    }

    // MovesContainer wouldn't be necessary if it weren't requiered by isMoveLegal method
    private static boolean isPositionInCheck(Board b, Color kingsColor, MoveTracker mt, int ver, int hor) {
        for (int verFrom = 0; verFrom < b.getVerSize(); verFrom++) {
            for (int horFrom = 0; horFrom < b.geHorSize(); horFrom++) {
                Piece attackingPiece = b.getPiece(verFrom, horFrom);
                if (attackingPiece == null || attackingPiece.getColor() == kingsColor) {
                    continue;
                }
                // King can't check another King since itselve it would be in check
                // therefore King can't move into another's King range
                if (attackingPiece instanceof King
                        && (verFrom == ver || verFrom + 1 == ver || verFrom - 1 == ver)
                        && (horFrom == hor || horFrom + 1 == hor || horFrom - 1 == hor)) {
                    return true;
                } else if (!(attackingPiece instanceof King) && isMoveLegal(attackingPiece.getColor(), b, mt, new MoveType(), verFrom, horFrom, ver, hor)) { // if attacking piece can move from it's posistion verFrom, horFrom to position we are checking i.e. ver, hor
//                    System.out.println("verFrom = " + verFrom + ", horFrom = " + horFrom); // debug
                    System.out.println("Position " + ver + ", " + hor + " is checked."); // debug
                    return true;
                }
            }
        }

        return false;
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
//            System.out.print(verLeft + "," + (horLeft + i + 1) + "; "); // debug
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
//            System.out.print((verDown + i + 1) + "," + horDown + "; "); // debug
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
//            System.out.print((verLeft + i * d + d) + "," + (horLeft + i + 1) + "; "); // debug
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
