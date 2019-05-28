/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class NoPawnAtGivenPositionException extends RuntimeException {
    
    public NoPawnAtGivenPositionException(){
        super();
    }
    
    public NoPawnAtGivenPositionException(String s){
        super(s);
    }
}
