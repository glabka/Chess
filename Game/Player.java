package Game;

import pieces.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class Player {
    
   private final Color c;
    
    public Player(Color c){
        this.c = c;
    }
    
    public Color getColor(){
        return c;
    }
    
}
