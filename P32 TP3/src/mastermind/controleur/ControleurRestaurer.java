package mastermind.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import mastermind.AppMastermind;

public class ControleurRestaurer implements ActionListener {

	private final AppMastermind appMastermind;
	public ControleurRestaurer(AppMastermind appMastermind) {
		this.appMastermind = appMastermind;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String r�ponse = JOptionPane.showInputDialog(null, "Entrez le nom de la sauvegarde :");
		
		if(r�ponse == null || r�ponse.length() == 0) {
			JOptionPane.showMessageDialog(null, "Pas de nom de sauvegarde sp�cifi� !", "Erreur !", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			switch (JOptionPane.showConfirmDialog(null, "Vous allez perdre votre progression en restaurant une sauvegarde. �tes-vous s�r ?", "Restauration",
					JOptionPane.YES_NO_OPTION)) {
			case JOptionPane.YES_OPTION:
				this.appMastermind.restaurerVueMastermindFichier(r�ponse);
				JOptionPane.showMessageDialog(null, "Restauration termin�e !", "Restauration", JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				break;
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
		}
	}

}
