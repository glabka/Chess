package Game;

public class Move {
	private int verFrom;
	private int horFrom;
	private int verTo;
	private int horTo;
	
	public Move(int verFrom, int horFrom, int verTo, int horTo) {
		this.verFrom = verFrom;
		this.horFrom = horFrom;
		this.verTo = verTo;
		this.horTo = horTo;
	}
	
	public Move(int[] coord) {
        if (coord == null || coord.length != 4) {
            throw new IllegalArgumentException("coord can't be null and have to refer to array of length 4.");
        }
		this.verFrom = coord[0];
		this.horFrom = coord[1];
		this.verTo = coord[2];
		this.horTo = coord[3];
	}
	
	public Move getReversedMove() {
		return new Move(verTo, horTo, verFrom, horFrom);
	}
	
	public int getVerFrom() {
		return verFrom;
	}
	
	public int getHorFrom() {
		return horFrom;
	}
	
	public int getVerTo() {
		return verTo;
	}
	
	public int getHorTo() {
		return horTo;
	}
	
	public int[] getCoordinates() {
		int[] coord = new int[4];
		coord[0] = getVerFrom();
		coord[1] = getHorFrom();
		coord[2] = getVerTo();
		coord[3] = getHorTo();
		return coord;
	}
	
}
