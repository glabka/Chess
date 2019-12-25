package Game;

import pieces.Color;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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

	public Board cloneBoard() {
		return new Board(this);
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
		printBoard(false);
	}

	public void printBoardDebug() {
		printBoard(true);
	}

	private void printBoard(boolean debug) {
		String fillingChar1 = "*"; // "╳", "◯"
		String fillingChar2 = ".";
		System.out.print(" ");
		for (int i = 0; i < board.length; i++) {
			if (!debug) {
				System.out.print(fillingChar1 + indexToPositionChar(i));
			} else {
				System.out.print(fillingChar1 + i);
			}
		}
		System.out.println("");

		for (int i = 0; i < board.length; i++) {
			if (!debug) {
				System.out.print(indexToPositionNum(i));
			} else {
				System.out.print(i);
			}
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(" ");
				if (board[i][j] == null) {
					if (((i + 1) % 2 == 0 && (j + 1) % 2 == 0) || ((i + 1) % 2 != 0 && (j + 1) % 2 != 0)) {
						System.out.print(fillingChar1);
					} else {
						System.out.print(fillingChar2);
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

	// row is <-1, 7>
	// does not end with end of the line
	public void printRow(int row, boolean debug) {
		String fillingChar1 = "*"; // "╳", "◯"
		String fillingChar2 = ".";
		if (row == -1) {
//			System.out.print(" ");
			for (int i = 0; i < board.length; i++) {
				if (!debug) {
					System.out.print(fillingChar1 + indexToPositionChar(i));
				} else {
					System.out.print(fillingChar1 + i);
				}
			}
		} else {
			for (int i = 0; i < board[row].length; i++) {
				if (i > 0) {
					System.out.print(" ");
				} else if (debug) {
					System.out.print(row);
				} else {
					System.out.print(indexToPositionNum(row) + " ");
				}
				if (board[row][i] == null) {
					if (((row + 1) % 2 == 0 && (i + 1) % 2 == 0) || ((row + 1) % 2 != 0 && (i + 1) % 2 != 0)) {
						System.out.print(fillingChar1);
					} else {
						System.out.print(fillingChar2);
					}
				} else if (board[row][i] instanceof King && board[row][i].getColor() == Color.BLACK) {
					System.out.print("♚");
				} else if (board[row][i] instanceof King && board[row][i].getColor() == Color.WHITE) {
					System.out.print("♔");
				} else if (board[row][i] instanceof Queen && board[row][i].getColor() == Color.BLACK) {
					System.out.print("♛");
				} else if (board[row][i] instanceof Queen && board[row][i].getColor() == Color.WHITE) {
					System.out.print("♕");
				} else if (board[row][i] instanceof Rook && board[row][i].getColor() == Color.BLACK) {
					System.out.print("♜");
				} else if (board[row][i] instanceof Rook && board[row][i].getColor() == Color.WHITE) {
					System.out.print("♖");
				} else if (board[row][i] instanceof Bishop && board[row][i].getColor() == Color.BLACK) {
					System.out.print("♝");
				} else if (board[row][i] instanceof Bishop && board[row][i].getColor() == Color.WHITE) {
					System.out.print("♗");
				} else if (board[row][i] instanceof Knight && board[row][i].getColor() == Color.BLACK) {
					System.out.print("♞");
				} else if (board[row][i] instanceof Knight && board[row][i].getColor() == Color.WHITE) {
					System.out.print("♘");
				} else if (board[row][i] instanceof Pawn && board[row][i].getColor() == Color.BLACK) {
					System.out.print("♟");
				} else if (board[row][i] instanceof Pawn && board[row][i].getColor() == Color.WHITE) {
					System.out.print("♙");
				}
			}
		}
	}

	public void printDebugCodeBoard() {
		System.out.println("{");
		for (int i = 0; i < 8; i++) {
			System.out.print("{");
			for (int j = 0; j < 8; j++) {
				Piece piece = this.board[i][j];
				if (piece != null) {
					System.out.print("(Piece) new " + piece.getClass().getName() + "(Color." + piece.getColor() + ")");
				} else {
					System.out.print("null");
				}
				if (j < 7) {
					System.out.print(",");
				}
			}
			System.out.println("}");
			if (i < 7) {
				System.out.print(",");
			}
		}
		System.out.println("}");
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

		board[ver][hor] = new Queen(piece.getColor());
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

    @Override
    public boolean equals(Object o) {
    	if(this == o) {
    		return true;
    	}
    	
    	if(!(o instanceof Board)) {
    		return false;
    	}
    	
    	Board b = (Board) o;
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			Piece piece = b.getPiece(i, j);
    			Piece piece2 = getPiece(i, j);
				if(piece == null) {
					if(piece2 != null) {
						return false;
					}
				} else if (!piece.equals(piece2)) {
					return false;
				}
			}
		}
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	double val = 0;
    	for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				val += Math.pow(10, i * 10 + j) * (board[i][j] == null ? 0 : board[i][j].hashCode());
			}
		}
    	return Double.hashCode(val);
    }	
}
