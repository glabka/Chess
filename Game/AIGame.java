package Game;

import AI.AI1;
import AI.AI2;
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
		String[] errorMessageHolder = new String[1];
		AbstractAI AI1 = new AI1();
		AbstractAI AI2 = new AI2();
		int counter = 0;
		while (true) {
//            b.printBoard();
			counter++;
			System.out.println("counter = " + counter);
			b.printBoardDebug(); // debug
//			b.printDebugCodeBoard(); // debug
//			System.out.println("isKingCheckmated(" + currentPlayer.getColor() + ") = " + Rules.isKingCheckmated(currentPlayer.getColor(), b, mt)); // debug
//			System.out.println("isKingCheckmated(" + Color.opossiteColor(currentPlayer.getColor()) + ") = " + Rules.isKingCheckmated(Color.opossiteColor(currentPlayer.getColor()), b, mt)); // debug
//			System.out.println("isKingInStalemate(" + currentPlayer.getColor() + ") = " + Rules.isKingInStalemate(currentPlayer.getColor(), b, mt)); // debug
//			System.out.println("isKingInStalemate(" + Color.opossiteColor(currentPlayer.getColor()) + ") = " + Rules.isKingInStalemate(Color.opossiteColor(currentPlayer.getColor()), b, mt)); // debug
			if (!Rules.isKingCheckmated(currentPlayer.getColor(), b, mt)) {
				if (!Rules.isKingInStalemate(currentPlayer.getColor(), b, mt)) {
					Move mv;
					if (currentPlayer.getColor() == Color.BLACK) {
//						mv = AI1.nextMove(gs.cloneGameState(), currentPlayer, b.cloneBoard(), mt.cloneMoveTracker());
						mv = AI1.nextMove(gs.cloneGameState(), currentPlayer, b.cloneBoard(), mt.cloneMoveTracker());
					} else {
						mv = AI2.nextMove(gs.cloneGameState(), currentPlayer, b.cloneBoard(), mt.cloneMoveTracker());
//						System.out.println("mv == " + mv + " " + mv.getVerFrom() + " "+ mv.getHorFrom() + " " + mv.getVerTo() + " " + mv.getHorTo()); // debug
					}
					if (Rules.move(gs, currentPlayer, b, mt, errorMessageHolder, mv)) {
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
