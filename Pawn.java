/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class Pawn extends Piece{
    
    private final Color color;
    
    public Pawn(Color color){
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
    
}
