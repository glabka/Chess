package Game;

import pieces.Color;
import pieces.Bishop;
import pieces.Pawn;
import pieces.Rook;
import pieces.King;
import pieces.Piece;
import pieces.Knight;
import pieces.Queen;

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

	public static boolean move(GameState gs, Player p, Board b, MoveTracker mt, String[] errorMessage, Move mv) {
		if(mv == null) {
			throw new IllegalArgumentException("Move can't be null.");
		}
		return move(gs, p, b, mt, errorMessage, mv.getVerFrom(), mv.getHorFrom(), mv.getVerTo(), mv.getHorTo());
	}

	// TODO:
	// * check (adding states to game propably)
	public static boolean move(GameState gs, Player p, Board b, MoveTracker mt, String[] errorMessage, int iFrom,
			char cFrom, int iTo, char cTo) {
		int verFrom = Board.positionNumToIndex(iFrom);
		int horFrom = Board.positionCharToIndex(cFrom);
		int verTo = Board.positionNumToIndex(iTo);
		int horTo = Board.positionCharToIndex(cTo);
		return move(gs, p, b, mt, errorMessage, verFrom, horFrom, verTo, horTo);
	}

	// MoveTracker is used to rember moves necessary for castling and en passant
	// whereas MoveType is used to contain information about move in form of
	// MovesEnum
	public static boolean move(GameState gs, Player p, Board b, MoveTracker mt, String[] errorMessage, int verFrom,
			int horFrom, int verTo, int horTo) {
		if(gs == null) {
			throw new IllegalArgumentException("GameState can't be null.");
		}
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

		checkGameState(p.getColor(), b, mt, gs);
		if (gs.getState() == GameStateEnum.STALEMATE || gs.getState() == GameStateEnum.BLACK_KING_CHECKMATED
				|| gs.getState() == GameStateEnum.WHITE_KING_CHECKMATED) {
			return false;
		}

		MoveType mType = new MoveType();
		if (isMoveLegal(p.getColor(), b, mt, mType, errorMessage, verFrom, horFrom, verTo, horTo)) {
			movePiece(false, b, mt, mType, verFrom, horFrom, verTo, horTo);
			checkGameState(p.getColor(), b, mt, gs);
			return true;
		} else {
			return false;
		}
	}

	public static void checkGameState(Color playersColor, Board b, MoveTracker mt, GameState gs) {
		if (isKingInStalemate(playersColor, b, mt)) {
			gs.setState(GameStateEnum.STALEMATE);
		} else if (isKingCheckmated(playersColor, b, mt)) {
			if (playersColor == Color.WHITE) {
				gs.setState(GameStateEnum.WHITE_KING_CHECKMATED);
			} else {
				gs.setState(GameStateEnum.BLACK_KING_CHECKMATED);
			}
		} else if(isKingInCheck(b, playersColor, mt)) {
			if (playersColor == Color.WHITE) {
				gs.setState(GameStateEnum.WHITE_KING_IN_CHECK);
			} else {
				gs.setState(GameStateEnum.BLACK_KING_IN_CHECK);
			}
		} else {
			gs.setState(GameStateEnum.NORMAL);
		}
	}

	// moves piece on board without checking whether move is legal
	// calledfromWithin - true if function is called within class Rules, false if
	// called by external class
	// calledFromWithin is important in case pawn would be promoted - that should
	// happened only when external
	// call is done
	private static void movePiece(boolean calledFromWithin, Board b, MoveTracker mt, MoveType mType, int verFrom,
			int horFrom, int verTo, int horTo) {
		Piece movingPiece = b.getPiece(verFrom, horFrom);

		// Tracking whether King or rooks moved (important for castling)
		storeKingsOrRooksMove(movingPiece, mt, horFrom); // TODO: why is there only one coordinate?

		mt.storeMove(movingPiece, verFrom, horFrom, verTo, horTo);
		if (mType.getMoveType() == MovesEnum.STANDARD) {
			b.movePiece(verFrom, horFrom, verTo, horTo);
		} else if (mType.getMoveType() == MovesEnum.PAWN_PROMOTION) {
			b.movePiece(verFrom, horFrom, verTo, horTo);
			if (!calledFromWithin) {
				b.promotePawn(verTo, horTo);
			}
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
	}

	private static void storeKingsOrRooksMove(Piece piece, MoveTracker mt, int horFrom) {
		if (piece instanceof King) {
			mt.storeKingMoved(piece.getColor());
		} else if (piece instanceof Rook) {
			if (horFrom == 0) {
				mt.storeQueensideRookMoved(piece.getColor());
			} else {
				mt.storeKingsideRookMoved(piece.getColor());
			}
		}
	}

	public static boolean isMoveLegal(Color playersColor, Board b, MoveTracker mt, MoveType mType,
			String[] errorMessage, int verFrom, int horFrom, int verTo, int horTo) {
		// Piece can be null if there's no piece on the location
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		if (movingPiece == null) {
			return false;
		}

		if (playersColor != movingPiece.getColor()) {
			if (errorMessage != null && errorMessage.length != 0) {
				errorMessage[0] = "It is " + playersColor + " players move.";
			}
			return false;
		}

		// checking if king will be in check after the move is done
		MovesEnum oldMoveType = mType.getMoveType(); // if moves unveils check, than the move have to be reversed and
														// mType has to be restored
		if (isMoveLegalNotConsideringCheck(movingPiece, b, mt, mType, verFrom, horFrom, verTo, horTo)) {
			Board testB = new Board(b);
			MoveTracker testMT = new MoveTracker(mt);
			MoveType testMType = new MoveType(mType);
			movePiece(true, testB, testMT, testMType, verFrom, horFrom, verTo, horTo);
			if (isKingInCheck(testB, playersColor, testMT)) {
//                System.out.println("King would be in check."); // debug
				mType.setMoveType(oldMoveType);
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

	private static boolean isMoveLegalNotConsideringCheck(Piece movingPiece, Board b, MoveTracker mt, MoveType mType,
			int verFrom, int horFrom, int verTo, int horTo) {
		if (movingPiece instanceof King) {
			return isKingsMoveLegal(b, mt, mType, verFrom, horFrom, verTo, horTo);
		} else if (movingPiece instanceof Queen) {
			mType.setMoveType(MovesEnum.STANDARD);
			return isQueensMoveLegal(b, verFrom, horFrom, verTo, horTo);
		} else if (movingPiece instanceof Bishop) {
			mType.setMoveType(MovesEnum.STANDARD);
			return isBishopsMoveLegal(b, verFrom, horFrom, verTo, horTo);
		} else if (movingPiece instanceof Knight) {
			mType.setMoveType(MovesEnum.STANDARD);
			return isKnightsMoveLegal(b, verFrom, horFrom, verTo, horTo);
		} else if (movingPiece instanceof Rook) {
			mType.setMoveType(MovesEnum.STANDARD);
			return isRooksMoveLegal(b, verFrom, horFrom, verTo, horTo);
		} else { // pawn
			return isPawnsMoveLegal(b, mt, mType, verFrom, horFrom, verTo, horTo);
		}
	}

	private static boolean isKingInCheck(Board b, Color kingsColor, MoveTracker mt) {
		int kingVer = 0, kingHor = 0;
		for (int i = 0; i < b.getVerSize(); i++) {
			for (int j = 0; j < b.getHorSize(); j++) {
				if (b.getPiece(i, j) instanceof King && b.getPiece(i, j).getColor() == kingsColor) {
					kingVer = i;
					kingHor = j;
				}
			}
		}
		return isPositionInCheck(b, kingsColor, mt, kingVer, kingHor);
	}

	private static boolean basicLegalityCheck(Color playersColor, Board b, int verFrom, int horFrom, int verTo,
			int horTo) {
		// Piece can be null if there's no piece on the location
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		Piece standingPiece = b.getPiece(verTo, horTo);

		if (playersColor != movingPiece.getColor()) {
			return false;
		}

		if (standingPiece != null && movingPiece.getColor() == standingPiece.getColor()) {
			return false;
		}
		return true;
	}

	private static boolean isKingsMoveLegal(Board b, MoveTracker mt, MoveType mType, int verFrom, int horFrom,
			int verTo, int horTo) {
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		Color kingsColor = b.getPiece(verFrom, horFrom).getColor();

		if (!basicLegalityCheck(movingPiece.getColor(), b, verFrom, horFrom, verTo, horTo)) {
			return false;
		}

		if (!isPositionInCheck(b, kingsColor, mt, verTo, horTo)
				&& (verFrom == verTo || verFrom + 1 == verTo || verFrom - 1 == verTo)
				&& (horFrom == horTo || horFrom + 1 == horTo || horFrom - 1 == horTo)) {
			mType.setMoveType(MovesEnum.STANDARD);
			return true;
		} // white kingside castling
		else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor) && verFrom == 7 && horFrom == 4
				&& verTo == 7 && horTo == 6) {
			Piece rook = b.getPiece(7, 7);
			if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
					&& isPathBetweenFree(b, verFrom, horFrom, 7, 7)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 1)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 2)) {
				mType.setMoveType(MovesEnum.WHITE_KINGSIDE_CASTLING);
				return true;
			}
		} // black kingside castling
		else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor) && verFrom == 0 && horFrom == 4
				&& verTo == 0 && horTo == 6) {
			Piece rook = b.getPiece(0, 7);
			if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
					&& isPathBetweenFree(b, verFrom, horFrom, 0, 7)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 1)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom + 2)) {
				mType.setMoveType(MovesEnum.BLACK_KINGSIDE_CASTLING);
				return true;
			}
		} // white queenside castling
		else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor) && verFrom == 7 && horFrom == 4
				&& verTo == 7 && horTo == 2) {
			Piece rook = b.getPiece(7, 0);
			if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
					&& isPathBetweenFree(b, verFrom, horFrom, 7, 0)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 1)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 2)) {
				mType.setMoveType(MovesEnum.WHITE_QUEENSIDE_CASTLING);
				return true;
			}
		} // black queenside castling
		else if (!mt.didKingMove(kingsColor) && !mt.didKingsideRookMove(kingsColor) && verFrom == 0 && horFrom == 4
				&& verTo == 0 && horTo == 2) {
			Piece rook = b.getPiece(0, 0);
			if (rook instanceof Rook && rook.getColor() == kingsColor // rook exists
					&& isPathBetweenFree(b, verFrom, horFrom, 0, 0)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 1)
					&& !isPositionInCheck(b, kingsColor, mt, verFrom, horFrom - 2)) {
				mType.setMoveType(MovesEnum.BLACK_QUEENSIDE_CASTLING);
				return true;
			}
		}
		return false;
	}

	private static boolean isQueensMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		if (!basicLegalityCheck(movingPiece.getColor(), b, verFrom, horFrom, verTo, horTo)) {
			return false;
		} else if (onSameDiagonal(verFrom, horFrom, verTo, horTo)
				&& isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
			return true;
		} else if (verFrom == verTo && horFrom != horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
			return true;
		} else if (verFrom != verTo && horFrom == horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isBishopsMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		if (!basicLegalityCheck(movingPiece.getColor(), b, verFrom, horFrom, verTo, horTo)) {
			return false;
		} else if (onSameDiagonal(verFrom, horFrom, verTo, horTo)
				&& isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isKnightsMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		if (!basicLegalityCheck(movingPiece.getColor(), b, verFrom, horFrom, verTo, horTo)) {
			return false;
		} else if ((verFrom + 2 == verTo || verFrom - 2 == verTo) && (horFrom + 1 == horTo || horFrom - 1 == horTo)) {
			return true;
		} else if ((verFrom + 1 == verTo || verFrom - 1 == verTo) && (horFrom + 2 == horTo || horFrom - 2 == horTo)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isRooksMoveLegal(Board b, int verFrom, int horFrom, int verTo, int horTo) {
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		if (!basicLegalityCheck(movingPiece.getColor(), b, verFrom, horFrom, verTo, horTo)) {
			return false;
		} else if (verFrom == verTo && horFrom != horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
			return true;
		} else if (verFrom != verTo && horFrom == horTo && isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isPawnsMoveLegal(Board b, MoveTracker mt, MoveType mType, int verFrom, int horFrom,
			int verTo, int horTo) {
		Piece movingPiece = b.getPiece(verFrom, horFrom);
		// Piece can be null if there's no piece on the location
		Piece standingPiece = b.getPiece(verTo, horTo);

		if (!basicLegalityCheck(movingPiece.getColor(), b, verFrom, horFrom, verTo, horTo)) {
			return false;
		}

		if (movingPiece.getColor() == Color.BLACK) {
			// first move - two steps
			if (verFrom == 1 && verTo == 3 && horFrom == horTo && standingPiece == null
					&& isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
				mType.setMoveType(MovesEnum.STANDARD);
				return true;
			} // moving forward
			else if (verFrom + 1 == verTo && horFrom == horTo && standingPiece == null) {
				if (verTo == 7) {
					mType.setMoveType(MovesEnum.PAWN_PROMOTION);
				} else {
					mType.setMoveType(MovesEnum.STANDARD);
				}
				return true;
			} // normal capture
			else if (verFrom + 1 == verTo && (horFrom + 1 == horTo || horFrom - 1 == horTo) && standingPiece != null
					&& standingPiece.getColor() != movingPiece.getColor()) {
				if (verTo == 7) {
					mType.setMoveType(MovesEnum.PAWN_PROMOTION);
				} else {
					mType.setMoveType(MovesEnum.STANDARD);
				}
				return true;
			} else if (mt.getPiece() instanceof Pawn && mt.getVerFrom() == 6 && mt.getVerTo() == 4 // last move of Pawn was double step
					&& verFrom == 4 && verTo == 5 && (horFrom - 1 == mt.getHorTo() || horFrom + 1 == mt.getHorTo()) && horTo == mt.getHorTo()) {
				mType.setMoveType(MovesEnum.EN_PASSANT);
				return true;
			} else {
				return false;
			}
		} else { // movingPiece == Color.WHITE
			// first move - two steps
			if (verFrom == 6 && verTo == 4 && horFrom == horTo && standingPiece == null
					&& isPathBetweenFree(b, verFrom, horFrom, verTo, horTo)) {
				mType.setMoveType(MovesEnum.STANDARD);
				return true;
			} // moving forward
			else if (verFrom - 1 == verTo && horFrom == horTo && standingPiece == null) {
				if (verTo == 0) {
					mType.setMoveType(MovesEnum.PAWN_PROMOTION);
				} else {
					mType.setMoveType(MovesEnum.STANDARD);
				}
				return true;
			} // normal capture
			else if (verFrom - 1 == verTo && (horFrom + 1 == horTo || horFrom - 1 == horTo) && standingPiece != null
					&& standingPiece.getColor() != movingPiece.getColor()) {
				if (verTo == 0) {
					mType.setMoveType(MovesEnum.PAWN_PROMOTION);
				} else {
					mType.setMoveType(MovesEnum.STANDARD);
				}
				return true;
			} else if (mt.getPiece() instanceof Pawn && mt.getVerFrom() == 1 && mt.getVerTo() == 3 // last move of Pawn was double step
					&& verFrom == 3 && verTo == 2 && (horFrom - 1 == mt.getHorTo() || horFrom + 1 == mt.getHorTo()) && horTo == mt.getHorTo()) {
				mType.setMoveType(MovesEnum.EN_PASSANT);
				return true;
			} else {
				return false;
			}
		}

	}

	// MovesContainer wouldn't be necessary if it weren't requiered by isMoveLegal
	// method
	private static boolean isPositionInCheck(Board b, Color kingsColor, MoveTracker mt, int ver, int hor) {
		for (int verFrom = 0; verFrom < b.getVerSize(); verFrom++) {
			for (int horFrom = 0; horFrom < b.getHorSize(); horFrom++) {
				Piece attackingPiece = b.getPiece(verFrom, horFrom);
				if (attackingPiece == null || attackingPiece.getColor() == kingsColor) {
					continue;
				}
				// King can't check another King since itselve it would be in check
				// therefore King can't move into another's King range
				if (attackingPiece instanceof King && (verFrom == ver || verFrom + 1 == ver || verFrom - 1 == ver)
						&& (horFrom == hor || horFrom + 1 == hor || horFrom - 1 == hor)) {
					return true;
				} else if (!(attackingPiece instanceof King) && isMoveLegalNotConsideringCheck(attackingPiece, b, mt,
						new MoveType(), verFrom, horFrom, ver, hor)) { // if attacking piece can move from it's
																		// posistion verFrom, horFrom to position we are
																		// checking i.e. ver, hor
//                    System.out.println("verFrom = " + verFrom + ", horFrom = " + horFrom); // debug
//                    System.out.println("Position " + ver + ", " + hor + " is checked."); // debug
					return true;
				}
			}
		}

		return false;
	}

	public static boolean isKingCheckmated(Color playersColor, Board b, MoveTracker mt) {
		if (isKingInCheck(b, playersColor, mt)) {
			if (canLegalMoveBeDone(playersColor, b, mt)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public static boolean isKingInStalemate(Color playersColor, Board b, MoveTracker mt) {
		if (isKingInCheck(b, playersColor, mt)) {
			return false;
		} else {
			if (canLegalMoveBeDone(playersColor, b, mt)) {
				return false;
			} else {
				return true;
			}
		}
	}

	private static boolean canLegalMoveBeDone(Color playersColor, Board b, MoveTracker mt) {
		Piece movingPiece = null;
		for (int i = 0; i < b.getVerSize(); i++) {
			for (int j = 0; j < b.getHorSize(); j++) {
				movingPiece = b.getPiece(i, j);
				if (movingPiece != null && movingPiece.getColor() == playersColor) {
					// finding out if there's coordinates where to do legal move
					for (int k = 0; k < b.getVerSize(); k++) {
						for (int l = 0; l < b.getHorSize(); l++) {
							if (isMoveLegal(playersColor, b, mt, new MoveType(), null, i, j, k, l)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean isPathBetweenFree(Board b, int verI, int horI, int verJ, int horJ) {
		if (verI != verJ && horI != horJ && !onSameDiagonal(verI, horI, verJ, horJ)) {
			throw new IllegalArgumentException(
					"Entered positions are not on the same line (vertical, horizontal or diagonal).");
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
