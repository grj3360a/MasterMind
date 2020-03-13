package chessquito.pieces;

import java.util.ArrayList;
import java.util.List;

import chessquito.Echiquier;
import chessquito.Position;
import ihm.CouleurChess;
import ihm.TypePiece;

public abstract class Piece {
	private CouleurChess couleur;
	private TypePiece type;
	private Position position;
	private int valeur;
	private boolean deplacementMultiple;

	public Piece(final TypePiece nom, final CouleurChess couleur, final int valeur, final boolean deplacementMultiple) {
		this.valeur = valeur;
		this.couleur = couleur;
		this.type = nom;
		this.deplacementMultiple = deplacementMultiple;
	}

	public abstract List<Position> getVecteursDeplacement();

	public TypePiece getType() {
		return this.type;
	}

	public CouleurChess getCouleur() {
		return this.couleur;
	}

	public Position getPosition() {
		return this.position;
	}

	public int getValeur() {
		return this.valeur;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	public char initiale() {
		if (this.couleur == CouleurChess.NOIR)
			return this.type.toString().toUpperCase().charAt(0);
		
		return this.type.toString().toLowerCase().charAt(0);
	}

	public boolean estDeplacementValide(final Position arrivee, final Echiquier echiquier) {
		final List<Position> positionsAccessibles = this.positionsAccessibles(echiquier);
		final boolean resultat = positionsAccessibles.contains(arrivee);
		return resultat;
	}

	@SuppressWarnings("unused")
	private List<Position> positionsAccessibles(final Echiquier echiquier) {
		final List<Position> vecteursDeplacementsPossibles = this.getVecteursDeplacement();
		final List<Position> liste = new ArrayList<Position>();
		
		for (final Position pos : vecteursDeplacementsPossibles) {
			Position positionPiece = this.getPosition();
			boolean progressionPossible;
			do {
				final Position aux = positionPiece = new Position(positionPiece.valX() + pos.valX(), positionPiece.valY() + pos.valY());
				if (echiquier.estPositionValide(positionPiece)) {
					final Piece piece = echiquier.getPiece(positionPiece);
					if (piece == null) {
						liste.add(positionPiece);
						progressionPossible = this.deplacementMultiple;
					} else {
						progressionPossible = false;
						if (piece.getCouleur().equals(this.getCouleur())) {
							continue;
						}
						liste.add(positionPiece);
					}
				} else {
					progressionPossible = false;
				}
			} while (progressionPossible);
		}
		return liste;
	}
}
