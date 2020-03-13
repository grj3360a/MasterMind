// 

// 

package chessquito.exception;

public class PositionInvalideException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public PositionInvalideException() {
        super("la position n'est pas valide");
    }
}
