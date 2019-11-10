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

public class BoardEvaluation1 {
	
	public static int getEvaluation(Player p, Board b, MoveTracker mt) {
		int value = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				value += getPiecesValue(p, b, mt, i, j);
			}
		}
		return value;
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
		
		if (piece instanceof King) {
            value *= 0; // TODO
        } else if (piece instanceof Queen) {
        	value *= 15; 
        } else if (piece instanceof Rook) {
        	value *= 5; 
        } else if (piece instanceof Bishop) {
        	value *= 10;
        } else if (piece instanceof Knight) {
        	value *= 7;
        } else if (piece instanceof Pawn) {
            value *= 1;
        }
		
		return value;
	}
	
}
