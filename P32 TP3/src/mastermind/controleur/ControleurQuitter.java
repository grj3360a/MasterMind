package mastermind.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mastermind.AppMastermind;

public class ControleurQuitter implements ActionListener {

	public ControleurQuitter(AppMastermind appMastermind) {
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (JOptionPane.showConfirmDialog(null, "Vous allez perdre votre progression en quittant.", "Quitter",
				JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.YES_OPTION:
			System.exit(0);
			break;
		default:
			break;
		}
	}

}
