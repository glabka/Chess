/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class Board {

    Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        board[positionNumToIndex(8)][positionCharToIndex('a')] = new Rook(Color.BLACK);
        board[positionNumToIndex(8)][positionCharToIndex('b')] = new Knight(Color.BLACK);
        board[positionNumToIndex(8)][positionCharToIndex('c')] = new Bishop(Color.BLACK);
        board[positionNumToIndex(8)][positionCharToIndex('d')] = new Queen(Color.BLACK);
        board[positionNumToIndex(8)][positionCharToIndex('e')] = new King(Color.BLACK);
        board[positionNumToIndex(8)][positionCharToIndex('f')] = new Bishop(Color.BLACK);
        board[positionNumToIndex(8)][positionCharToIndex('g')] = new Knight(Color.BLACK);
        board[positionNumToIndex(8)][positionCharToIndex('h')] = new Rook(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            board[positionNumToIndex(7)][positionCharToIndex((char) ('a' + i))] = new Pawn(Color.BLACK);
        }
        board[positionNumToIndex(1)][positionCharToIndex('a')] = new Rook(Color.WHITE);
        board[positionNumToIndex(1)][positionCharToIndex('b')] = new Knight(Color.WHITE);
        board[positionNumToIndex(1)][positionCharToIndex('c')] = new Bishop(Color.WHITE);
        board[positionNumToIndex(1)][positionCharToIndex('d')] = new Queen(Color.WHITE);
        board[positionNumToIndex(1)][positionCharToIndex('e')] = new King(Color.WHITE);
        board[positionNumToIndex(1)][positionCharToIndex('f')] = new Bishop(Color.WHITE);
        board[positionNumToIndex(1)][positionCharToIndex('g')] = new Knight(Color.WHITE);
        board[positionNumToIndex(1)][positionCharToIndex('h')] = new Rook(Color.WHITE);
        for (int i = 0; i < 8; i++) {
            board[positionNumToIndex(2)][positionCharToIndex((char) ('a' + i))] = new Pawn(Color.WHITE);
        }
    }

    public Board(Piece[][] board) {
        if (board.length != 8) {
            throw new IllegalArgumentException("Board's dimentions have to be 8x8.");
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i].length != 8) {
                throw new IllegalArgumentException("Board's dimentions have to be 8x8.");
            }
        }
        this.board = board;
    }

    public static int positionNumToIndex(int n) {
        if (n < 1 || n > 8) {
            throw new IllegalArgumentException();
        }
        return -n + 8;
    }

    public static int indexToPositionNum(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException();
        }
        return -index + 8;
    }

    public static int positionCharToIndex(char c) {
        c = Character.toLowerCase(c);
        if (c < 97 || c > 104) {
            throw new IllegalArgumentException();
        }
        return c - 97;
    }

    public static char indexToPositionChar(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException();
        }
        return (char) (index + 97);
    }

    public void printBoard() {
        System.out.print(" ");
        for (int i = 0; i < board.length; i++) {
//            System.out.print("╳" + indexToPositionChar(i));
            System.out.print("╳" + i);// debug
        }
        System.out.println("");

        for (int i = 0; i < board.length; i++) {
//            System.out.print(indexToPositionNum(i));
            System.out.print(i); // debug
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" ");
                if (board[i][j] == null) {
                    if (((i + 1) % 2 == 0 && (j + 1) % 2 == 0) || ((i + 1) % 2 != 0 && (j + 1) % 2 != 0)) {
                        System.out.print("╳");
                    } else {
                        System.out.print("◯");
                    }
                } else if (board[i][j] instanceof King && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("♚");
                } else if (board[i][j] instanceof King && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("♔");
                } else if (board[i][j] instanceof Queen && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("♛");
                } else if (board[i][j] instanceof Queen && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("♕");
                } else if (board[i][j] instanceof Rook && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("♜");
                } else if (board[i][j] instanceof Rook && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("♖");
                } else if (board[i][j] instanceof Bishop && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("♝");
                } else if (board[i][j] instanceof Bishop && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("♗");
                } else if (board[i][j] instanceof Knight && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("♞");
                } else if (board[i][j] instanceof Knight && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("♘");
                } else if (board[i][j] instanceof Pawn && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("♟");
                } else if (board[i][j] instanceof Pawn && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("♙");
                }
            }
            System.out.println("");
        }
    }

    public boolean move(Player p, MovesContainer con, int iFrom, char cFrom, int iTo, char cTo) {
        int verFrom = positionNumToIndex(iFrom);
        int horFrom = positionCharToIndex(cFrom);
        int verTo = positionNumToIndex(iTo);
        int horTo = positionCharToIndex(cTo);
        return move(p, con, verFrom, horFrom, verTo, horTo);
    }

    public boolean move(Player p, MovesContainer con, int verFrom, int horFrom, int verTo, int horTo) {
        if (p == null) {
            throw new IllegalArgumentException("Player can't be null.");
        }
        if (con == null) {
            throw new IllegalArgumentException("MovesContainer can't be null.");
        }
        if (!isIndexOnBoard(verFrom)) {
            throw new IllegalArgumentException("position verFrom = " + verFrom + " is out of bounds.");
        } else if (!isIndexOnBoard(horFrom)) {
            throw new IllegalArgumentException("position horFrom = " + horFrom + " is out of bounds.");
        } else if (!isIndexOnBoard(verTo)) {
            throw new IllegalArgumentException("position verTo = " + verTo + " is out of bounds.");
        } else if (!isIndexOnBoard(horTo)) {
            throw new IllegalArgumentException("position horTo = " + horTo + " is out of bounds.");
        }
        if (Rules.isMoveLegal(p, this, con, verFrom, horFrom, verTo, horTo)) {
            board[verTo][horTo] = board[verFrom][horFrom];
            board[verFrom][horFrom] = null;
            return true;
        } else {
            return false;
        }
    }

    public Piece getPiece(int ver, int hor) {
        return board[ver][hor];
    }

//    public PieceEnum getPieceType(int ver, int hor){
//        if(!isIndexOnBoard(ver)){
//            throw new IllegalArgumentException("position ver = " + ver + " is out of bounds.");
//        } else if(!isIndexOnBoard(hor)){
//            throw new IllegalArgumentException("position hor = " + hor + " is out of bounds.");
//        }
//        
//        if(board[ver][hor] == null){
//            return null;
//        } else if(board[ver][hor] instanceof King){
//            return PieceEnum.KING;
//        } else if(board[ver][hor] instanceof Queen){
//            return PieceEnum.QUEEN;
//        } else if(board[ver][hor] instanceof Bishop){
//            return PieceEnum.BISHOP;
//        } else if(board[ver][hor] instanceof Knight){
//            return PieceEnum.KNIGHT;
//        } else if(board[ver][hor] instanceof Rook){
//            return PieceEnum.ROOK;
//        } else { // Pawn
//            return PieceEnum.PAWN;
//        }
//    }
//    
//    public Color getPieceColor(int ver, int hor){
//        if(board[ver][hor] == null){
//            return null;
//        } else{
//            return board[ver][hor].getColor();
//        }
//    }
    public static boolean isIndexOnBoard(int index) {
        if (index >= 0 || index < 8) {
            return true;
        } else {
            return false;
        }
    }

}
