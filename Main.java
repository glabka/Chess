
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
//        // Testing of moves - user input        
//        
//        Board b = new Board();
//        Player p1 = new Player(Color.WHITE);
//        Player p2 = new Player(Color.BLACK);
//        Player currentPlayer = p1;
//        MovesContainer con = new MovesContainer();
//        int[] positions = new int[4];
//        while (true) {
//            b.printBoard();
//            Input.read4Int(positions);
//            if (b.move(currentPlayer, con, positions[0], positions[1], positions[2], positions[3])) {
//                if (currentPlayer == p1) {
//                    currentPlayer = p2;
//                } else {
//                    currentPlayer = p1;
//                }
//            } else {
//                System.out.println("The move is not possible.");
//            }
//
//        }
        // Testing of moves - input in code
        // moves
        LinkedList<Integer[]> moves = new LinkedList<>();
        Integer[][] positions = {{6, 4, 4, 4},
        {1, 4, 2, 4}, 
        {7, 4, 6, 4},
        {0, 3, 3, 6},
        {6, 4, 5, 4}};

        Board b = new Board();
        Player p1 = new Player(Color.WHITE);
        Player p2 = new Player(Color.BLACK);
        Player currentPlayer = p1;
        MovesContainer con = new MovesContainer();

        for (int i = 0; i < positions.length; i++) {
            b.printBoard();
            System.out.println(positions[i][0] + " " + positions[i][1] + " " + positions[i][2] + " " + positions[i][3]);
            if (b.move(currentPlayer, con, positions[i][0], positions[i][1], positions[i][2], positions[i][3])) {
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
