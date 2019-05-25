
import java.util.LinkedList;

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
        if (true) {
//        // Testing of moves - user input        
            Board b = new Board();
            Player p1 = new Player(Color.WHITE);
            Player p2 = new Player(Color.BLACK);
            Player currentPlayer = p1;
            MoveTracker mt = new MoveTracker();
            int[] positions = new int[4];
            while (true) {
                b.printBoard();
                Input.read4Int(positions);
                if (Rules.move(currentPlayer, b, mt, positions[0], positions[1], positions[2], positions[3])) {
                    if (currentPlayer == p1) {
                        currentPlayer = p2;
                    } else {
                        currentPlayer = p1;
                    }
                } else {
                    System.out.println("The move is not possible.");
                }

            }
            //
            //
            //
            //
        } else {
            // Testing of moves - input in code
            // moves
            Integer[][] posInCheck = {{6, 4, 4, 4},
            {1, 4, 2, 4},
            {7, 4, 6, 4},
            {0, 3, 3, 6},
            {6, 4, 5, 4}};
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

            Integer[][] positions = whiteKingsideCastling;
            for (int i = 0; i < positions.length; i++) {
                b.printBoard();
                System.out.println(positions[i][0] + " " + positions[i][1] + " " + positions[i][2] + " " + positions[i][3]);
                if (Rules.move(currentPlayer, b, mt, positions[i][0], positions[i][1], positions[i][2], positions[i][3])) {
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
        }

    }
}
