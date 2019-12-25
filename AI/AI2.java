package AI;

import java.util.ArrayList;

import Game.Board;
import Game.GameState;
import Game.Move;
import Game.MoveTracker;
import Game.Player;
import Game.Rules;
import pieces.Color;
import pieces.Pawn;
import pieces.Piece;

public class AI2 extends AbstractAI {

	@Override
	public Move nextMove(GameState gs, Player p, Board b, MoveTracker mt) {
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Integer> boardEvaluations = new ArrayList<Integer>();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				getMovesAndEvals(gs, p, b, mt, i, j, moves, boardEvaluations);
			}
		}

		int maxEval = Integer.MIN_VALUE;
		Move bestMv = null;
		for (int i = 0; i < boardEvaluations.size(); i++) {
			Board newBoard = b.cloneBoard(); // board with move to be played

			Rules.move(gs, p, newBoard, mt, null, moves.get(i));
			int eval = recursiveSearchCurrentPlayer(3, 0, gs.cloneGameState(), p, newBoard, mt.cloneMoveTracker());
			if (maxEval <= eval) {
				maxEval = eval;
				bestMv = moves.get(i);
			}
		}

		return bestMv;
	}

	// recursion on already played move. Returns biggest BoardEvaluation. depth is indexed from 0
	private int recursiveSearchCurrentPlayer(int maxDepth, int depth, GameState gs, Player currentPlayer, Board b,
			MoveTracker mt) {
		if (maxDepth == depth) {
			return BoardEvaluation2.getEvaluation(currentPlayer, b, mt);
		} else if (depth != 0) {
			Move mv = nextStep(gs, currentPlayer, b, mt);
			if (mv == null) { // current player is in checkmate or stalemate
				return Integer.MIN_VALUE;
			}
			Board newBoard = b.cloneBoard();
			Rules.move(gs, currentPlayer, newBoard, mt, null, mv);
			return recursiveSearchOppositePlayer(maxDepth, depth + 1, gs.cloneGameState(), currentPlayer, newBoard, mt.cloneMoveTracker());
		} else {
			return recursiveSearchOppositePlayer(maxDepth, depth + 1, gs.cloneGameState(), currentPlayer, b.cloneBoard(), mt.cloneMoveTracker());
		}
	}

	private int recursiveSearchOppositePlayer(int maxDepth, int depth, GameState gs, Player currentPlayer, Board b,
			MoveTracker mt) {
		if (maxDepth == depth) {
			return BoardEvaluation2.getEvaluation(currentPlayer, b, mt);
		} else {
			Player opponent = getOpponent(currentPlayer);
			Move mv = nextStep(gs, opponent, b, mt);
			if (mv == null) { // opponent is in checkmate or stalemate
				return Integer.MAX_VALUE;
			}
			Rules.move(gs, opponent, b, mt, null, mv);
			return recursiveSearchCurrentPlayer(maxDepth, depth + 1, gs.cloneGameState(), currentPlayer, b.cloneBoard(), mt.cloneMoveTracker());
		}
	}

	private void printDebugBoard(int depth, Board b) {
		for (int j = 0; j < depth; j++) {
			System.out.print("\t");
		}
		System.out.println("depth == " + depth);
		for (int i = -1; i < 8; i++) {
			for (int j = 0; j < depth; j++) {
				System.out.print("\t");
			}
			b.printRow(i, true);
			System.out.println("");
		}
	}
	
	private Player getOpponent(Player p) {
		if (p.getColor() == Color.BLACK) {
			return new Player(Color.WHITE);
		} else {
			return new Player(Color.BLACK);
		}
	}

	private Move nextStep(GameState gs, Player p, Board b, MoveTracker mt) {
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Integer> boardEvaluations = new ArrayList<Integer>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				getMovesAndEvals(gs, p, b, mt, i, j, moves, boardEvaluations);
			}
		}

		int maxEval = Integer.MIN_VALUE;
		Move bestMv = null;
		for (int i = 0; i < boardEvaluations.size(); i++) {
			int eval = boardEvaluations.get(i);
			if (maxEval <= eval) {
				maxEval = eval;
				bestMv = moves.get(i);
			}
		}

		return bestMv;
	}

	/**
	 * Adds moves to parameter moves and adds board evaluations for this moves to
	 * boardEvals
	 * 
	 * @param gs
	 * @param p
	 * @param b
	 * @param mt
	 * @param verFrom
	 * @param horFrom
	 * @param moves
	 * @param boardEvals
	 */
	private void getMovesAndEvals(GameState gs, Player p, Board b, MoveTracker mt, int verFrom, int horFrom,
			ArrayList<Move> moves, ArrayList<Integer> boardEvals) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Board newBoard = b.cloneBoard();
				Move mv = new Move(verFrom, horFrom, i, j);
				if (Rules.move(gs.cloneGameState(), p, newBoard, mt.cloneMoveTracker(), null, mv)) {
					moves.add(mv);
					boardEvals.add(BoardEvaluation2.getEvaluation(p, newBoard, mt));
				}
			}
		}
	}

}
