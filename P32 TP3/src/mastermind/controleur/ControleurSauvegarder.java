package mastermind.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JOptionPane;

import mastermind.AppMastermind;

public class ControleurSauvegarder implements ActionListener {

	private final AppMastermind appMastermind;

	public ControleurSauvegarder(AppMastermind appMastermind) {
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
			this.appMastermind.sauvegarderVueMastermindFichier(r�ponse, false);

			JOptionPane.showMessageDialog(null, "Sauvegarde termin�e !", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
		
		} catch (FileAlreadyExistsException e) {
			switch (JOptionPane.showConfirmDialog(null, "Voulez-vous �craser le fichier pr�c�dent ?", "Sauvegarde",
					JOptionPane.YES_NO_OPTION)) {
			case JOptionPane.YES_OPTION:
				try {
					this.appMastermind.sauvegarderVueMastermindFichier(r�ponse, true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur lors de la sauvegarde �cras�e !", JOptionPane.ERROR_MESSAGE);
				}
				break;
			default:
				break;
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
		}
	}

}
