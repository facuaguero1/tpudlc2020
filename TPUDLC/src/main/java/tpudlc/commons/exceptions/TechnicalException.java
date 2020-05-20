
package tpudlc.commons.exceptions;


public class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new technical exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public TechnicalException( String message, Throwable cause ) {
        super(message, cause);
    }

    /**
     * Instantiates a new technical exception.
     * 
     * @param message the message
     */
    public TechnicalException( String message ) {
        super(message);
    }
    
    public TechnicalException( Throwable cause ){
        super(cause);
    }
}
