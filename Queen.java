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
    
    private final Color color;
    
    public Queen(Color color){
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
