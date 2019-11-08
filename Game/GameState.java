package Game;

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
