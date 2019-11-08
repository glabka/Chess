package Game;

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
