package AI;

import Game.Board;
import Game.Move;
import Game.MoveTracker;
import Game.Player;

public abstract class AbstractAI {
	public abstract Move nextMove(Player p, Board b, MoveTracker mt);
}
