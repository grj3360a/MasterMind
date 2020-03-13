package ihm;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chessquito.Position;

public class VueChessquito extends JPanel {
	private static final long serialVersionUID = -8374140689824329959L;
	
	private final JTextArea messages;
	private final JPanel panneauPlateau;
	private final int size;
	
	/**
	 * Construit l'interface graphique du jeu de Chessquito
	 */
	public VueChessquito(int size) throws IllegalArgumentException {
		if(size <= 0) throw new IllegalArgumentException();
		ControleurChessquito controleur = new ControleurChessquito(this, size);
		this.size = size;
		this.setLayout(new BorderLayout());
		
		this.add(this.panneauPlateau = new JPanel(new GridLayout(size, size)));
		
		CouleurChess couleur = CouleurChess.NOIR;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				CaseIHM caseIHM;
				this.panneauPlateau.add(caseIHM = new CaseIHM(new Position(x, y), couleur = couleur.inverse()));
				caseIHM.addActionListener(controleur::actionPerformed);
			}
			couleur = couleur.inverse();
		}
		
		this.messages = new JTextArea(4, 40);
		this.messages.setEditable(false);
		
		this.add(new JScrollPane(this.messages), BorderLayout.SOUTH);
		controleur.rafraichir();
	}
	
	/**
	 * Positionne une piece sur une case de coordonnees i et j de l'echequier
	 * @param piece piece a positionner
	 * @param i indice de ligne de la case
	 * @param j indice de colonne de la case
	 */
	public void positionnerPiece(PieceIHM piece, Position pos) throws IllegalArgumentException {
		if(pos == null) throw new IllegalArgumentException();
		if(!pos.isInBound(size)) throw new IllegalArgumentException(pos.toString());

		CaseIHM caseIhm = (CaseIHM) this.panneauPlateau.getComponent(this.size * pos.valX() + pos.valY());
		caseIhm.setIcon(piece);
	}
	
	public void afficherMessage(String message) throws IllegalArgumentException {
		if(message == null) throw new IllegalArgumentException();
		this.messages.append(message + "\n");
		this.messages.setSelectionEnd(this.messages.getText().length());
	}
	
}
