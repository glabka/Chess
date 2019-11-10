package AI;

import java.util.ArrayList;

import Game.Board;
import Game.GameState;
import Game.Move;
import Game.MoveTracker;
import Game.Player;
import Game.Rules;

public class AI1 extends AbstractAI {

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
			int eval = boardEvaluations.get(i);
			if(maxEval <= eval) {
				maxEval = eval;
				bestMv = moves.get(i);
			}
		}
		
		return bestMv;
	}
	
	/**
	 * Adds moves to parameter moves and adds board evaluations for this moves to boardEvals
	 * @param gs
	 * @param p
	 * @param b
	 * @param mt
	 * @param verFrom
	 * @param horFrom
	 * @param moves
	 * @param boardEvals
	 */
	private void getMovesAndEvals(GameState gs, Player p, Board b, MoveTracker mt, int verFrom, int horFrom, ArrayList<Move> moves, ArrayList<Integer> boardEvals){
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Board newBoard = b.cloneBoard();
				Move mv = new Move(verFrom, horFrom, i, j);
				if(Rules.move(gs, p, newBoard, mt, mv)) {
					moves.add(mv);
					boardEvals.add(BoardEvaluation1.getEvaluation(p, newBoard, mt));
				}
			}
		}
	}

}
