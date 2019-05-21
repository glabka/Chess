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
        
            Board b = new Board();
//            System.out.println(Board.positionNumToIndex(5));
//            System.out.println(Board.positionCharToIndex('b'));
//            System.out.println(Board.indexToPositionNum(5));
//            System.out.println(Board.indexToPositionChar(2));
            b.printBoard();

//            Player p = new Player(Color.BLACK);
//            Color c = p.getColor();
//            c = Color.WHITE;
//            System.out.println("Player's color = " + p.getColor());


    }
    
}
