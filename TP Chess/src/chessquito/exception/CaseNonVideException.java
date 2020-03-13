package chessquito.exception;

public class CaseNonVideException extends ChessquitoException
{
    private static final long serialVersionUID = 1L;
    
    public CaseNonVideException() {
        super("Il y a une piece sur la case");
    }
}
