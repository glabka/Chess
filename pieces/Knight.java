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
public class Knight extends Piece{
    
    public Knight(Color color) {
        super(color);
    }
    
    @Override
    public boolean equals(Object o) {
    	if(this == o) {
    		return true;
    	}
    	
    	if(!(o instanceof Knight)) {
    		return false;
    	}
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	int val = 3;
    	return this.getColor() == Color.BLACK ? val : -val;
    }
}
