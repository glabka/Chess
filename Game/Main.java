package Game;

import pieces.Color;
import java.util.LinkedList;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author glabka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // https://codereview.stackexchange.com/questions/71790/design-a-chess-game-using-object-oriented-principles
//        https://www.quora.com/What-are-some-good-project-ideas-for-an-undergraduate-object-oriented-programming-course-using-Java

//            Board b = new Board();
//            System.out.println(Board.positionNumToIndex(5));
//            System.out.println(Board.positionCharToIndex('b'));
//            System.out.println(Board.indexToPositionNum(5));
//            System.out.println(Board.indexToPositionChar(2));
//            b.printBoard();
//            Player p = new Player(Color.BLACK);
//            Color c = p.getColor();
//            c = Color.WHITE;
//            System.out.println("Player's color = " + p.getColor());
        //            Piece[][] board = new Piece[8][8];
//            board[1][2] = new Pawn(Color.BLACK);
//            board[1][6] = new Pawn(Color.WHITE);
//            board[3][5] = new King(Color.WHITE);
//            board[6][2] = new Rook(Color.WHITE);
//            board[4][5] = new Queen(Color.BLACK);
//            Board b2 = new Board(board);
//            b2.printBoard();
//        Rules r1 = new Rules();
//        r1.testing(b2);
//
//
//
        // for all cases below it is black players turn
        Piece[][] stalemate1 = new Piece[8][8];
        stalemate1[0][7] = new King(Color.BLACK);
        stalemate1[1][5] = new King(Color.WHITE);
        stalemate1[2][6] = new Queen(Color.WHITE);

        Piece[][] stalemate2 = new Piece[8][8];
        stalemate2[0][5] = new King(Color.BLACK);
        stalemate2[1][5] = new Pawn(Color.WHITE);
        stalemate2[2][5] = new King(Color.WHITE);

        Piece[][] checkmate1 = new Piece[8][8];
        checkmate1[2][5] = new King(Color.WHITE);
        checkmate1[2][7] = new King(Color.BLACK);
        checkmate1[7][7] = new Rook(Color.WHITE);

        Piece[][] checkmate2 = new Piece[8][8];
        checkmate2[2][0] = new King(Color.BLACK);
        checkmate2[2][1] = new Queen(Color.WHITE);
        checkmate2[3][2] = new King(Color.WHITE);

        Piece[][] checkmate3 = new Piece[8][8];
        checkmate3[3][7] = new Queen(Color.WHITE);
        checkmate3[5][7] = new King(Color.BLACK);
        checkmate3[6][5] = new King(Color.WHITE);

        Piece[][] checkmate4 = new Piece[8][8];
        checkmate4[2][5] = new King(Color.WHITE);
        checkmate4[2][7] = new King(Color.BLACK);
        checkmate4[7][7] = new Queen(Color.WHITE);

        Piece[][] notACheckmate1 = new Piece[8][8];
        notACheckmate1[2][0] = new King(Color.BLACK);
        notACheckmate1[2][1] = new Queen(Color.WHITE);
        notACheckmate1[4][2] = new King(Color.WHITE);

        // one step from checkmate
        Piece[][] oneStepFromCheckmate1 = new Piece[8][8];
        oneStepFromCheckmate1[2][5] = new King(Color.WHITE);
        oneStepFromCheckmate1[2][7] = new King(Color.BLACK);
        oneStepFromCheckmate1[7][7] = new Queen(Color.WHITE);
        oneStepFromCheckmate1[4][0] = new Rook(Color.BLACK);

        // for all cases below it is white players move
        Piece[][] checkmate5 = new Piece[8][8];
        checkmate5[0][1] = new Queen(Color.WHITE);
        checkmate5[1][5] = new Pawn(Color.BLACK);
        checkmate5[1][6] = new King(Color.BLACK);
        checkmate5[2][2] = new Pawn(Color.BLACK);
        checkmate5[2][6] = new Pawn(Color.BLACK);
        checkmate5[3][1] = new Pawn(Color.BLACK);
        checkmate5[3][4] = new Knight(Color.WHITE);
        checkmate5[3][7] = new Pawn(Color.BLACK);
        checkmate5[4][1] = new Bishop(Color.BLACK);
        checkmate5[4][7] = new Pawn(Color.WHITE);
        checkmate5[5][1] = new Bishop(Color.BLACK);
        checkmate5[5][2] = new Knight(Color.BLACK);
        checkmate5[6][2] = new Rook(Color.BLACK);
        checkmate5[6][6] = new Pawn(Color.WHITE);
        checkmate5[7][2] = new King(Color.WHITE);

        Piece[][] pawnPromotion = new Piece[8][8];
        pawnPromotion[1][0] = new Pawn(Color.WHITE);
        pawnPromotion[6][0] = new Pawn(Color.BLACK);
        
        
        boolean userInput = false;
        boolean codeInput = false;
        boolean realPlayersGame = false;
        boolean AIGame = true;
        if (userInput) {
//        // Testing of moves - user input    
            Board b = new Board(pawnPromotion);
            Player p1 = new Player(Color.WHITE);
            Player p2 = new Player(Color.BLACK);
            Player currentPlayer = p1; // BE AWARE
            MoveTracker mt = new MoveTracker();
            GameState gs = new GameState();
            int[] positions = new int[4];
            while (true) {
                b.printBoard();
                Input.read4Int(positions);
                if (Rules.move(gs, currentPlayer, b, mt, positions[0], positions[1], positions[2], positions[3])) {
                    if (currentPlayer == p1) {
                        currentPlayer = p2;
                    } else {
                        currentPlayer = p1;
                    }
                } else {
                    if (gs.getState() != GameStateEnum.NORMAL) {
                        System.out.println(gs.getState());
                    }
                    System.out.println("The move is not possible.");
                }

            }
            //
            //
            //
            //
        } else if (codeInput) {
            // Testing of moves - input in code
            // moves
            Integer[][] posInCheck = {{6, 4, 4, 4},
            {1, 4, 2, 4},
            {7, 4, 6, 4},
            {0, 3, 3, 6},
            {6, 4, 5, 4}};
            Integer[][] revealingKingIntoCheck = {{6, 7, 5, 7},
            {1, 4, 2, 4},
            {5, 7, 4, 7},
            {0, 5, 4, 1},
            {6, 3, 5, 3}};
            Integer[][] twoEnPassants = {{6, 7, 4, 7},
            {1, 0, 3, 0},
            {4, 7, 3, 7},
            {3, 0, 4, 0},
            {6, 1, 4, 1},
            {4, 0, 5, 1},
            {6, 0, 5, 1},
            {1, 6, 3, 6},
            {3, 7, 2, 6}
            };
            Integer[][] whiteKingsideCastling = {{7, 6, 5, 5},
            {1, 0, 2, 0},
            {6, 6, 5, 6},
            {1, 1, 2, 1},
            {7, 5, 5, 7},
            {1, 2, 2, 2},
            {7, 4, 7, 6}};

            Board b = new Board();
            Player p1 = new Player(Color.WHITE);
            Player p2 = new Player(Color.BLACK);
            Player currentPlayer = p1;
            MoveTracker mt = new MoveTracker();
            GameState gs = new GameState();

            Integer[][] positions = whiteKingsideCastling;
            for (int i = 0; i < positions.length; i++) {
                b.printBoard();
                System.out.println(positions[i][0] + " " + positions[i][1] + " " + positions[i][2] + " " + positions[i][3]);
                if (Rules.move(gs, currentPlayer, b, mt, positions[i][0], positions[i][1], positions[i][2], positions[i][3])) {
                    if (currentPlayer == p1) {
                        currentPlayer = p2;
                    } else {
                        currentPlayer = p1;
                    }
                } else {
                    System.out.println("The move is not possible.");
                }
            }
            b.printBoard();
        } else if (realPlayersGame) {
            Game g = new Game();
            g.startGame();
        } else if (AIGame) {
        	AIGame AIG = new AIGame();
        	AIG.startGame();
        }

    }
}
