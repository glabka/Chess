/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class GameState {
    private GameStateEnum state = GameStateEnum.NORMAL;
    private String errorMessage;
    
    public GameState(){
        
    }
    
    public GameState(GameStateEnum state){
        this.state = state;
    }

    public void setState(GameStateEnum state) {
        this.state = state;
    }

    public GameStateEnum getState() {
        return state;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
    
}
