package AI;

import java.util.ArrayList;

import Game.Board;
import Game.GameState;
import Game.Move;
import Game.MoveTracker;
import Game.Player;
import Game.Rules;
import pieces.Color;

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
			int eval = recursiveSearchCurrentPlayer(1, 0, gs, p, b, mt);
			if (maxEval <= eval) {
				maxEval = eval;
				bestMv = moves.get(i);
			}
		}

		return bestMv;
	}

	// recursion on already played move. Returns biggest BoardEvaluation
	private int recursiveSearchCurrentPlayer(int maxDepth, int depth, GameState gs, Player currentPlayer, Board b,
			MoveTracker mt) {
		if (maxDepth == depth) {
			return BoardEvaluation2.getEvaluation(currentPlayer, b, mt);
		} else if (depth != 0) {
			Move mv = nextStep(gs, currentPlayer, b, mt);
			Board newBoard = b.cloneBoard();
			Rules.move(gs, currentPlayer, b, mt, null, mv);
			return recursiveSearchOppositePlayer(maxDepth, depth + 1, gs, currentPlayer, newBoard, mt);
		} else {
			return recursiveSearchOppositePlayer(maxDepth, depth + 1, gs, currentPlayer, b, mt);
		}
	}

	private int recursiveSearchOppositePlayer(int maxDepth, int depth, GameState gs, Player currentPlayer, Board b,
			MoveTracker mt) {
		if (maxDepth == depth) {
			return BoardEvaluation2.getEvaluation(currentPlayer, b, mt);
		} else {
			Player opponent = getOpponent(currentPlayer);
			Move mv = nextStep(gs, opponent, b, mt);
			Board newBoard = b.cloneBoard();
			Rules.move(gs, opponent, b, mt, null, mv);
			return recursiveSearchCurrentPlayer(maxDepth, depth + 1, gs, currentPlayer, newBoard, mt);
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
				if (Rules.move(gs, p, newBoard, mt, null, mv)) {
					moves.add(mv);
					boardEvals.add(BoardEvaluation2.getEvaluation(p, newBoard, mt));
				}
			}
		}
	}

}
