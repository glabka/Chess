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
    
    public GameState(GameState gs) {
    	this.state = gs.getState();
    	this.errorMessage = gs.getErrorMessage();
    }
    
    public GameState cloneGameState() {
    	return new GameState(this);
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
