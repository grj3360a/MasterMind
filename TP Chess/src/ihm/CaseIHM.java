package ihm;
import javax.swing.JButton;

import chessquito.Position;

public class CaseIHM extends JButton {
	private static final long serialVersionUID = 4719553321080145951L;
	
	private final Position pos;
	private final CouleurChess couleur;
	
	public CaseIHM(Position pos, CouleurChess couleur) {
		this.pos = pos;
		this.couleur = couleur;
		this.setBackground(couleur.getColor());
	}

	public Position getPosition() {
		return this.pos;
	}
	
	public CouleurChess getCouleur() {
		return this.couleur;
	}
	
}
