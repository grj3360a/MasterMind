// 

// 

package chessquito.exception;

public class NomPieceException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public NomPieceException() {
        super("Nom de piece inexistant");
    }
}
