package Game;

import pieces.Color;
import pieces.Bishop;
import pieces.Pawn;
import pieces.Rook;
import pieces.King;
import pieces.Piece;
import pieces.Queen;
import pieces.Knight;

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

    Piece[][] board = new Piece[8][8];

    public Board() {
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

    public Board(Board b) {
        if (b == null) {
            throw new IllegalArgumentException("Board b can't be null.");
        }
        for (int i = 0; i < b.getVerSize(); i++) {
            for (int j = 0; j < b.getHorSize(); j++) {
                this.board[i][j] = b.getPiece(i, j);
            }
        }
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
            System.out.print("â•³" + indexToPositionChar(i));
//            System.out.print("â•³" + i);// debug
        }
        System.out.println("");

        for (int i = 0; i < board.length; i++) {
            System.out.print(indexToPositionNum(i));
//            System.out.print(i); // debug
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" ");
                if (board[i][j] == null) {
                    if (((i + 1) % 2 == 0 && (j + 1) % 2 == 0) || ((i + 1) % 2 != 0 && (j + 1) % 2 != 0)) {
                        System.out.print("â•³");
                    } else {
                        System.out.print("â—¯");
                    }
                } else if (board[i][j] instanceof King && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("â™š");
                } else if (board[i][j] instanceof King && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("â™”");
                } else if (board[i][j] instanceof Queen && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("â™›");
                } else if (board[i][j] instanceof Queen && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("â™•");
                } else if (board[i][j] instanceof Rook && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("â™œ");
                } else if (board[i][j] instanceof Rook && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("â™–");
                } else if (board[i][j] instanceof Bishop && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("â™�");
                } else if (board[i][j] instanceof Bishop && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("â™—");
                } else if (board[i][j] instanceof Knight && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("â™ž");
                } else if (board[i][j] instanceof Knight && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("â™˜");
                } else if (board[i][j] instanceof Pawn && board[i][j].getColor() == Color.BLACK) {
                    System.out.print("â™Ÿ");
                } else if (board[i][j] instanceof Pawn && board[i][j].getColor() == Color.WHITE) {
                    System.out.print("â™™");
                }
            }
            System.out.println("");
        }
    }

    public void movePiece(int verFrom, int horFrom, int verTo, int horTo) {
        checkPosition(verFrom, horFrom, verTo, horTo);

        Piece piece = board[verFrom][horFrom];
        if (piece == null) {
            throw new NoPieceAtGivenPositionException("No piece at position " + verFrom + ", " + horFrom + ".");
        }

        board[verTo][horTo] = piece;
        board[verFrom][horFrom] = null;
    }

    // needed for en passant
    public void deletePiece(int ver, int hor) {
        checkPosition(ver, hor);

        Piece piece = board[ver][hor];
        if (piece == null) {
            throw new NoPieceAtGivenPositionException("No piece at position " + ver + ", " + hor + ".");
        }

        board[ver][hor] = null;
    }

    public void promotePawn(int ver, int hor) {
        checkPosition(ver, hor);
        Piece piece = board[ver][hor];
        if (!(piece instanceof Pawn)) {
            throw new NoPawnAtGivenPositionException("No pawn at position " + ver + ", " + hor + ".");
        }
        
        Piece newPiece = null;
        while (true) {
            System.out.println("Enter q for queen, k for knight, b for bishop or r for rook.");
            char ch = Input.readChar();
            String input = String.valueOf(ch);
            switch (input.toLowerCase()) {
                case "k":
                    newPiece = new Knight(piece.getColor());
                    break;
                case "b":
                    newPiece = new Bishop(piece.getColor());
                    break;
                case "r":
                    newPiece = new Rook(piece.getColor());
                    break;
                case "q":
                    newPiece = new Queen(piece.getColor());
                    break;
            }
            if (newPiece != null) {
                break;
            }
        }
        board[ver][hor] = newPiece;
    }

    // throws exception if position is out of bounds
    private void checkPosition(int verFrom, int horFrom, int verTo, int horTo) {
        if (!Board.isIndexOnBoard(verFrom)) {
            throw new IllegalArgumentException("position verFrom = " + verFrom + " is out of bounds.");
        } else if (!Board.isIndexOnBoard(horFrom)) {
            throw new IllegalArgumentException("position horFrom = " + horFrom + " is out of bounds.");
        } else if (!Board.isIndexOnBoard(verTo)) {
            throw new IllegalArgumentException("position verTo = " + verTo + " is out of bounds.");
        } else if (!Board.isIndexOnBoard(horTo)) {
            throw new IllegalArgumentException("position horTo = " + horTo + " is out of bounds.");
        }
    }

    private void checkPosition(int ver, int hor) {
        if (!Board.isIndexOnBoard(ver)) {
            throw new IllegalArgumentException("position verFrom = " + ver + " is out of bounds.");
        } else if (!Board.isIndexOnBoard(hor)) {
            throw new IllegalArgumentException("position horFrom = " + hor + " is out of bounds.");
        }
    }

    public Piece getPiece(int ver, int hor) {
        return board[ver][hor];
    }

    public static boolean isIndexOnBoard(int index) {
        if (index >= 0 || index < 8) {
            return true;
        } else {
            return false;
        }
    }

    public int getVerSize() {
        return board.length;
    }

    public int getHorSize() {
        return board[0].length;
    }

}
