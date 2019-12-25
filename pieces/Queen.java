package pieces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class Queen extends Piece{
    
    public Queen(Color color) {
        super(color);
    }
    
    @Override
    public boolean equals(Object o) {
    	if(this == o) {
    		return true;
    	}
    	
    	if(!(o instanceof Queen)) {
    		return false;
    	}
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	int val = 5;
    	return this.getColor() == Color.BLACK ? val : -val;
    }
}
