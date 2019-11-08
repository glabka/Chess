package Game;

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
