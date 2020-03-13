// 

// 

package chessquito.exception;

public class PartieNonInitialiseeException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public PartieNonInitialiseeException() {
        super("La partie n'a pas ete initilalisee");
    }
}
