package chessquito.exception;

public class CaseVideException extends ChessquitoException {
	private static final long serialVersionUID = 1L;

	public CaseVideException() {
		super("Il n'y a aucune piece sur la case");
	}
}
