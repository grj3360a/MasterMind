// 

// 

package chessquito;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import chessquito.exception.CaseNonVideException;
import chessquito.exception.DeplacementInvalideException;
import chessquito.exception.PositionInvalideException;
import chessquito.pieces.Piece;
import ihm.CouleurChess;

public class Echiquier {
	private List<Piece> pieces;
	private final int size;

	public Echiquier(int size) {
		this.pieces = new ArrayList<Piece>();
		this.size = size;
	}

	private void enleverPiece(final Position position) {
		boolean trouve = false;
		final ListIterator<Piece> it1 = this.pieces.listIterator();
		while (!trouve && it1.hasNext()) {
			if (it1.next().getPosition().equals(position)) {
				trouve = true;
				it1.remove();
			}
		}
	}

	public Piece getPiece(final Position position) {
		for (final Piece p : this.pieces) {
			if (p.getPosition().equals(position)) {
				return p;
			}
		}
		return null;
	}

	public void setPiece(final Position position, final Piece piece)
			throws PositionInvalideException, CaseNonVideException {
		if (!this.estPositionValide(position)) {
			throw new PositionInvalideException();
		}
		if (!this.estCaseVide(position)) {
			throw new CaseNonVideException();
		}
		piece.setPosition(position);
		this.pieces.add(piece);
	}

	public boolean estPositionValide(final Position position) {
		final int x = position.valX();
		final int y = position.valY();
		return x >= 0 && x < this.size && y >= 0 && y < this.size;
	}

	public boolean estCaseVide(final Position position) throws PositionInvalideException {
		return this.getPiece(position) == null;
	}

	public int nombrePieces() {
		return this.pieces.size();
	}

	public int points(final CouleurChess couleur) {
		int nb = 0;
		for (final Piece p : this.pieces) {
			if (p.getCouleur() == couleur) {
				nb += p.getValeur();
			}
		}
		return nb;
	}

	public int nombrePieces(final CouleurChess couleur) {
		int nb = 0;
		for (final Piece p : this.pieces) {
			if (p.getCouleur() == couleur) {
				++nb;
			}
		}
		return nb;
	}

	public void deplacer(final Position depart, final Position arrivee) throws DeplacementInvalideException {
		final Piece pieceAttaquante = this.getPiece(depart);
		if (!pieceAttaquante.estDeplacementValide(arrivee, this)) {
			throw new DeplacementInvalideException();
		}
		final Piece pieceAttaquee = this.getPiece(arrivee);
		if (pieceAttaquee != null) {
			this.enleverPiece(arrivee);
		}
		pieceAttaquante.setPosition(arrivee);
	}

	@Override
	public String toString() {
		boolean estBlanc = false;
		String res = "  ";
		try {
			for (int i = 0; i < this.size; i++)
				res += "  " + i + " ";
			
			res += " \n  -----------------\n";
			for (int i = 0; i < this.size; ++i) {
				String contenu = " " + new Integer(i);
				contenu += " ";
				res += contenu + "|";
				for (int j = 0; j < this.size; ++j) {
					if (this.getPiece(new Position(i, j)) == null) {
						contenu = "   ";
					} else {
						contenu = " ";
						contenu += this.getPiece(new Position(i, j)).initiale();
						contenu += " ";
					}
					if (estBlanc) {
						res += contenu + "|";
					} else {
						res += contenu + "|";
					}
					estBlanc = !estBlanc;
				}
				estBlanc = !estBlanc;
				contenu = new Integer(i).toString();
				res += contenu + "\n";
				res += "  -----------------\n";
			}
			res +=  "    0   1   2   3   \n";
		} catch (Exception ex) {
		}
		return res;
	}
}
