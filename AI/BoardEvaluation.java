package AI;

import Game.Board;
import Game.MoveTracker;
import Game.Player;

public interface BoardEvaluation {

	public abstract int getEvaluation(Player p, Board b, MoveTracker mt);
	
}
