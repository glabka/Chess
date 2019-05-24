/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class NoPieceAtGivenPositionException extends RuntimeException{
 
    public NoPieceAtGivenPositionException(){
        super();
    }
    
    public NoPieceAtGivenPositionException(String s){
        super(s);
    }
}
