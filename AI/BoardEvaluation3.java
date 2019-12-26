package AI;

import Game.Board;
import Game.MoveTracker;
import Game.Player;
import pieces.Bishop;
import pieces.Color;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class BoardEvaluation3 implements BoardEvaluation {
	
	@Override
	public int getEvaluation(Player p, Board b, MoveTracker mt) {
		int value = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				value += getPositionCoeficient(p, b, i, j) * getPiecesValue(p, b, mt, i, j);
			}
		}
		return value;
	}
	
	private static int getPositionCoeficient(Player p, Board b, int ver, int hor) {
		// surrounding players own king		
		int[] kingsPos = new int[2];
		findKing(b, p.getColor(), kingsPos);
		
		if(isPieceShieldingKing(p.getColor(), ver, hor, kingsPos[0], kingsPos[1])) {
			return 3; 
		} else {
			return 1;
		}
	}
	
	private static boolean isPieceShieldingKing(Color col, int ver, int hor, int kingsVer, int kingsHor) {
		if(Color.WHITE == col) {
			return isPieceShieldingWhiteKing(ver, hor, kingsVer, kingsHor);
		} else {
			// by transposing vertical coordinates shielding of Black king is evaluated
			return isPieceShieldingWhiteKing(Board.transposeVerIndex(ver), hor, Board.transposeVerIndex(kingsVer), kingsHor);
		}
		
	}
	
	private static boolean isPieceShieldingWhiteKing(int ver, int hor, int kingsVer, int kingsHor) {
		if((ver == kingsVer || ver + 1 == kingsVer) && (hor == kingsHor || hor + 1 == kingsHor || hor - 1 == kingsHor)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static void findKing(Board b, Color col, int[] kingsPos) {
		Piece piece;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				piece = b.getPiece(i,j);
				if(piece instanceof King && piece.getColor() == col) {
					kingsPos[0] = i;
					kingsPos[1] = j;
				}
			}
		}
	}
	
	
	
	private static int getPiecesValue(Player p, Board b, MoveTracker mt, int ver, int hor) {
		Piece piece = b.getPiece(ver, hor);
		if(piece == null) {
			return 0;
		}
		
		int value;
		if (p.getColor() == piece.getColor()) {
			value = 1;
		} else {
			value = -1;
		}
		
		int queenValue = 150;
		if (piece instanceof King) {
            value *= 0; // TODO
        } else if (piece instanceof Queen) {
        	value *= queenValue; 
        } else if (piece instanceof Rook) {
        	value *= 50; 
        } else if (piece instanceof Bishop) {
        	value *= 100;
        } else if (piece instanceof Knight) {
        	value *= 70;
        } else if (piece instanceof Pawn) {
            value *= 1;
            int distFromFinish;
            if(piece.getColor() == Color.WHITE) {
            	distFromFinish = ver;
            } else {
            	distFromFinish = 7 - ver;
            }
            int[] distEval = {queenValue, queenValue / 2, 30, 20, 15, 12, 10};
            value = value * distEval[distFromFinish];
        }
		
		return value;
	}
}
