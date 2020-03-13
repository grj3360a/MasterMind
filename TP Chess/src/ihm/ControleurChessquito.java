package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import chessquito.PartieChessquito;
import chessquito.Position;
import chessquito.exception.ChessquitoException;
import chessquito.exception.PartieNonInitialiseeException;

public class ControleurChessquito implements ActionListener {
	
	private PartieChessquito model;
	private final VueChessquito vue;
	private final int size;
	
	private CaseIHM selectedButton;

	public ControleurChessquito(VueChessquito vue, int size) {
		this.model = new PartieChessquito(this.size = size);
		this.vue = vue;

		try {
			this.model.initialiser();
			this.model.remplirEchiquier();
		} catch (PartieNonInitialiseeException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(this.model.getEtat()) {
		
		case EN_COURS:
			if(!(ae.getSource() instanceof CaseIHM)) {
				return;
			}
			CaseIHM caseIHM = (CaseIHM) ae.getSource();
			
			if(this.selectedButton == null) {
				this.sélectionnerPion(caseIHM);
			} else {
				this.déplacerVersCase(caseIHM);
			}
			break;
			
		default:
			break;
			
		}
		
		this.rafraichir();
	}
	
	//trait1
	private void sélectionnerPion(CaseIHM selectedButton) {
		this.selectedButton = selectedButton;
	}
	
	//trait2
	private void déplacerVersCase(CaseIHM destination) {
		try {
			this.model.jouer(this.selectedButton.getPosition(), destination.getPosition());
		} catch (ChessquitoException e) {
			this.vue.afficherMessage(e.getMessage());
		}
		
		this.selectedButton = null;
	}

	/**
	 * rafraichit la vue du plateau graphique a partir du modele 
	 */
	protected void rafraichir() {
		try {
			//Piece selectedPiece = this.selectedButton == null ? null : this.model.getPiece(this.selectedButton.getPosition());
			
			for(Position pos : Position.getAll(this.size)) {
				if(this.model.getNomPiece(pos) == null) {
					this.vue.positionnerPiece(null, pos);
					continue;
				}
				
				PieceIHM piece;
				this.vue.positionnerPiece(piece = new PieceIHM(this.model.getNomPiece(pos), this.model.getCouleurPiece(pos), TypeImage.FIXE), pos);
				
				switch(this.model.getEtat()) {
				
				case EN_COURS:
					break;

				case NULLE:
				case BLANC_A_GAGNE:
				case NOIR_A_GAGNE:
					JOptionPane.showMessageDialog(null, "Partie gagné !\n" + this.model.getEtat().getMessage(), "Partie finie !", JOptionPane.INFORMATION_MESSAGE);
					this.model = new PartieChessquito(this.size);
					this.model.initialiser();
					this.model.remplirEchiquier();
					break;
					
				case NON_COMMENCEE:
					break;
				}
			}
			
		} catch (PartieNonInitialiseeException ex) {
			ex.printStackTrace();
		}
	}

}
