/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class MoveType {
    
    private MovesEnum mt;
    
    public MoveType(){
        
    }
    
    public MoveType(MovesEnum mt){
        this.mt = mt;
    }
    
    public MoveType(MoveType mType){
        this.mt = mType.getMoveType();
    }
    
    public void setMoveType(MovesEnum mt){
        this.mt = mt;
    }
    
    public MovesEnum getMoveType(){
        return mt;
    }
}
