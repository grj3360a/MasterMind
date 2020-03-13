package mastermind.controleur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import mastermind.ModeleMastermind;
import mastermind.VueMastermind;

public class ControleurMastermind implements ActionListener {
	
	public static final int NBMAX_COMBINAISONS = 10;
	
	enum Etat { DEBUT_COMBI, CHOIX_COULEUR, CHOIX_POSITION }
	
	private final VueMastermind vue;
	private final ModeleMastermind modele;
	
	private final int taille;
	private final int nb_couleurs;
	
	private Etat etat;
	private Color selectedColor;
	private int nbEssais;
	
	public ControleurMastermind(VueMastermind vue, int taille, int nb_couleurs) {
		this.vue = vue;
		this.taille = taille;
		this.nb_couleurs = nb_couleurs;
		this.modele = new ModeleMastermind(this.taille, this.nb_couleurs);
		this.etat = Etat.DEBUT_COMBI;
		this.nbEssais = 0;
		
		this.modele.genererCombinaison();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton button = (JButton) ae.getSource();
		
		switch(this.etat) {
		
		case DEBUT_COMBI:
			if(this.vue.appartientPalette(button)) {
				selectColor(button);
			}
			break;
			
		case CHOIX_POSITION:
			if(this.vue.appartientPalette(button)) {
				selectColor(button);
			} else if(this.vue.estBoutonValider(button) && this.vue.combinaisonComplete(nbEssais)) {
				this.displayStep();
				
				if(this.modele.nbChiffresBienPlaces(this.vue.combinaisonEnEntiers(nbEssais)) == this.taille ||
					nbEssais >= NBMAX_COMBINAISONS-1) {
					this.finished();
				} else {
					this.nextStep();
				}
			}
			break;
			
		case CHOIX_COULEUR:
			if(this.vue.appartientCombinaison(button, this.nbEssais)) {
				this.selectPosition(button);
			}
			break;
			
		}
	}
	
	//trait2
	private void selectColor(JButton button) {
		this.selectedColor = button.getBackground();
		this.etat = Etat.CHOIX_COULEUR;
	}

	//trait3
	private void selectPosition(JButton selected) {
		selected.setBackground(this.selectedColor);
		this.etat = Etat.CHOIX_POSITION;
	}

	//trait4
	private void displayStep() {
		int[] combi = this.vue.combinaisonEnEntiers(nbEssais);
		this.vue.afficherBP(nbEssais, this.modele.nbChiffresBienPlaces(combi));
		this.vue.afficherMP(nbEssais, this.modele.nbChiffresMalPlaces(combi));
	}

	//trait5
	private void nextStep() {
		nbEssais++;
		this.vue.activerCombinaison(nbEssais);
	}

	//trait6
	private void finished() {
		this.vue.afficherCombinaisonOrdinateur(this.modele.getCombinaison());
		this.vue.activerCombinaison(-1);
		JOptionPane.showInternalMessageDialog(this.vue, "Partie terminée !", "Partie terminée !", JOptionPane.INFORMATION_MESSAGE);
	}
}
