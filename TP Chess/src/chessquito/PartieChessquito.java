// 

// 

package chessquito;

import chessquito.exception.CaseNonVideException;
import chessquito.exception.CaseVideException;
import chessquito.exception.ChessquitoException;
import chessquito.exception.CouleurPieceException;
import chessquito.exception.DeplacementInvalideException;
import chessquito.exception.NomCouleurPieceException;
import chessquito.exception.NomPieceException;
import chessquito.exception.PartieNonInitialiseeException;
import chessquito.exception.PartieTermineeException;
import chessquito.exception.PositionInvalideException;
import chessquito.pieces.Cavalier;
import chessquito.pieces.Fou;
import chessquito.pieces.Piece;
import chessquito.pieces.Reine;
import chessquito.pieces.Tour;
import ihm.CouleurChess;
import ihm.TypePiece;

public class PartieChessquito {
	private Echiquier echiquier;
	private EtatPartie etat;
	private CouleurChess couleurJoueur;
	private int nbToursSansPrise;

	public PartieChessquito(int size) {
		this.echiquier = new Echiquier(size);
		this.etat = EtatPartie.NON_COMMENCEE;
	}

	public void initialiser() {
		this.etat = EtatPartie.EN_COURS;
		this.couleurJoueur = CouleurChess.BLANC;
		this.nbToursSansPrise = 0;
		System.out.println(this + "\n");
	}

	public void jouer(final Position depart, final Position arrivee)
			throws PartieNonInitialiseeException, PartieTermineeException, PositionInvalideException,
			CouleurPieceException, CaseVideException, DeplacementInvalideException {
		if (this.etat == EtatPartie.NON_COMMENCEE) {
			throw new PartieNonInitialiseeException();
		}
		final int nbPiecesAvant = this.echiquier.nombrePieces();
		if (this.etat != EtatPartie.EN_COURS && this.etat != EtatPartie.NON_COMMENCEE) {
			throw new PartieTermineeException();
		}
		if (!this.echiquier.estPositionValide(depart)) {
			throw new PositionInvalideException();
		}
		final Piece p = this.echiquier.getPiece(depart);
		if (p == null) {
			throw new CaseVideException();
		}
		final CouleurChess couleurPiece = p.getCouleur();
		if (couleurPiece != this.couleurJoueur) {
			throw new CouleurPieceException();
		}
		this.echiquier.deplacer(depart, arrivee);
		final int nbPiecesApres = this.echiquier.nombrePieces();
		if (nbPiecesAvant == nbPiecesApres) {
			++this.nbToursSansPrise;
		} else {
			this.nbToursSansPrise = 0;
		}
		if (this.echiquier.nombrePieces(CouleurChess.NOIR) == 0) {
			this.etat = EtatPartie.BLANC_A_GAGNE;
		} else if (this.echiquier.nombrePieces(CouleurChess.BLANC) == 0) {
			this.etat = EtatPartie.NOIR_A_GAGNE;
		} else if (this.nbToursSansPrise == 5) {
			final int scoreBlanc = this.echiquier.points(CouleurChess.BLANC);
			final int scoreNoir = this.echiquier.points(CouleurChess.NOIR);
			if (scoreBlanc == scoreNoir) {
				this.etat = EtatPartie.NULLE;
			} else if (scoreBlanc > scoreNoir) {
				this.etat = EtatPartie.BLANC_A_GAGNE;
			} else {
				this.etat = EtatPartie.NOIR_A_GAGNE;
			}
		}
		if (this.couleurJoueur == CouleurChess.BLANC) {
			this.couleurJoueur = CouleurChess.NOIR;
		} else {
			this.couleurJoueur = CouleurChess.BLANC;
		}
		System.out.println(this + "\n");
	}

	public void remplirEchiquier() throws PartieNonInitialiseeException {
		if (this.etat == EtatPartie.NON_COMMENCEE) {
			throw new PartieNonInitialiseeException();
		}
		try {
			this.echiquier.setPiece(new Position(0, 0), new Tour(CouleurChess.NOIR));
			this.echiquier.setPiece(new Position(0, 1), new Cavalier(CouleurChess.NOIR));
			this.echiquier.setPiece(new Position(0, 2), new Fou(CouleurChess.NOIR));
			this.echiquier.setPiece(new Position(0, 3), new Reine(CouleurChess.NOIR));
			this.echiquier.setPiece(new Position(3, 0), new Tour(CouleurChess.BLANC));
			this.echiquier.setPiece(new Position(3, 1), new Cavalier(CouleurChess.BLANC));
			this.echiquier.setPiece(new Position(3, 2), new Fou(CouleurChess.BLANC));
			this.echiquier.setPiece(new Position(3, 3), new Reine(CouleurChess.BLANC));
			System.out.println(this + "\n");
		} catch (ChessquitoException ex) {
			System.out.println(ex);
			System.exit(1);
		}
	}

	public EtatPartie getEtat() {
		return this.etat;
	}

	public CouleurChess getCouleurJoueur() throws PartieNonInitialiseeException {
		if (this.etat == EtatPartie.NON_COMMENCEE) {
			throw new PartieNonInitialiseeException();
		}
		return this.couleurJoueur;
	}

	@Override
	public String toString() {
		return this.echiquier.toString();
	}

	public Piece getPiece(final Position pos) throws PartieNonInitialiseeException {
		if (this.etat == EtatPartie.NON_COMMENCEE)
			throw new PartieNonInitialiseeException();
		
		return this.echiquier.getPiece(pos);
	}

	public CouleurChess getCouleurPiece(final Position pos) throws PartieNonInitialiseeException {
		if (this.etat == EtatPartie.NON_COMMENCEE)
			throw new PartieNonInitialiseeException();
		
		Piece p = this.echiquier.getPiece(pos);
		if (p == null)
			return null;
		
		return p.getCouleur();
	}

	public TypePiece getNomPiece(final Position pos) throws PartieNonInitialiseeException {
		if (this.etat == EtatPartie.NON_COMMENCEE)
			throw new PartieNonInitialiseeException();
		
		Piece p = this.echiquier.getPiece(pos);
		if (p != null)
			return p.getType();
		
		return null;
	}

	public void positionnerPiece(final TypePiece nomPiece, final CouleurChess couleurPiece, final Position pos)
			throws PartieNonInitialiseeException, NomCouleurPieceException, NomPieceException,
			PositionInvalideException, CaseNonVideException {

		switch (nomPiece) {
		case CAVALIER:
			this.echiquier.setPiece(pos, new Cavalier(couleurPiece));
			break;

		case FOU:
			this.echiquier.setPiece(pos, new Fou(couleurPiece));
			break;

		case REINE:
			this.echiquier.setPiece(pos, new Reine(couleurPiece));
			break;

		case TOUR:
			this.echiquier.setPiece(pos, new Tour(couleurPiece));
			break;

		case ROI:
		case PION:
			throw new NomPieceException();

		default:
			throw new NomPieceException();
		}

		System.out.println(this);
	}

	public boolean estJoueurNoirGagnant() {
		return this.etat == EtatPartie.NOIR_A_GAGNE;
	}

	public boolean estJoueurBlancGagnant() {
		return this.etat == EtatPartie.BLANC_A_GAGNE;
	}

	public boolean estPartieNulle() {
		return this.etat == EtatPartie.NULLE;
	}
}
