package AI;

import Game.Board;
import Game.GameState;
import Game.Move;
import Game.MoveTracker;
import Game.Player;

public abstract class AbstractAI {
	public abstract Move nextMove(GameState gs, Player p, Board b, MoveTracker mt);
}
