// 

// 

package chessquito.exception;

public class PartieTermineeException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public PartieTermineeException() {
        super("La partie est terminee");
    }
}
