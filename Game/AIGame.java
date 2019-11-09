package Game;

import AI.AI1;
import AI.AbstractAI;
import pieces.Color;

public class AIGame {
	public void startGame() {
        Board b = new Board();
        Player p1 = new Player(Color.WHITE);
        Player p2 = new Player(Color.BLACK);
        Player currentPlayer = p1; // BE AWARE
        MoveTracker mt = new MoveTracker();
        GameState gs = new GameState();
        AbstractAI AI = new AI1();
        while (true) {
            b.printBoard();
            if (!Rules.isKingCheckmated(currentPlayer.getColor(), b, mt)) {
                if (!Rules.isKingInStalemate(currentPlayer.getColor(), b, mt)) {
                    Move mv = AI.nextMove(currentPlayer, b.cloneBoard(), mt.cloneMoveTracker());
                    if (Rules.move(gs, currentPlayer, b, mt, mv)) {
                        if (currentPlayer == p1) {
                            currentPlayer = p2;
                        } else {
                            currentPlayer = p1;
                        }
                    } else {
                        System.out.println("The move is not possible.");
                    }
                } else {
                    System.out.println("Stalemate, the result is draw.");
                    break;
                }
            } else {
                System.out.println(Color.opossiteColor(currentPlayer.getColor()) + " player wins.");
                break;
            }
        }
    }
}
