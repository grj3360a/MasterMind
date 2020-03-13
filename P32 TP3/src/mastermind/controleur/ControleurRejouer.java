package mastermind.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mastermind.AppMastermind;

public class ControleurRejouer implements ActionListener {

	private final AppMastermind appMastermind;
	public ControleurRejouer(AppMastermind appMastermind) {
		this.appMastermind = appMastermind;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (JOptionPane.showConfirmDialog(null, "Vous allez perdre votre progression en rejouant", "Rejouer",
				JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.YES_OPTION:
			this.appMastermind.creerNouvelleVueMastermind();
			break;
		default:
			break;
		}
	}

}
