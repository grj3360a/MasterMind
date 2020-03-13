// 

// 

package chessquito.exception;

public class NomCouleurPieceException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public NomCouleurPieceException() {
        super("Nom de couleur de piece inexistant");
    }
}
