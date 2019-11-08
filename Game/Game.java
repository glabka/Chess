package Game;

import pieces.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author glabka
 */
public class Game {

    public void startGame() {
        Board b = new Board();
        Player p1 = new Player(Color.WHITE);
        Player p2 = new Player(Color.BLACK);
        Player currentPlayer = p1; // BE AWARE
        MoveTracker mt = new MoveTracker();
        GameState gs = new GameState();
        int[] positions = new int[4];
        while (true) {
            b.printBoard();
            if (!Rules.isKingCheckmated(currentPlayer.getColor(), b, mt)) {
                if (!Rules.isKingInStalemate(currentPlayer.getColor(), b, mt)) {
                    Input.readCoordinates(positions);
                    if (Rules.move(gs, currentPlayer, b, mt, positions[0], positions[1], positions[2], positions[3])) {
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
