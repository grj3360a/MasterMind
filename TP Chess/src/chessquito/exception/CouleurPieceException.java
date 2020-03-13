// 

// 

package chessquito.exception;

public class CouleurPieceException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public CouleurPieceException() {
        super("la piece jouee n'est pas de la bonne couleur");
    }
}
