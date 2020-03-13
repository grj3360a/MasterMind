// 

// 

package chessquito.exception;

public class DeplacementInvalideException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public DeplacementInvalideException() {
        super("le deplacement n'est pas valide");
    }
}
