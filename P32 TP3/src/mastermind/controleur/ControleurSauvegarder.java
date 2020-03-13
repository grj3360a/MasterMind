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
		String réponse = JOptionPane.showInputDialog(null, "Entrez le nom de la sauvegarde :");
		
		if(réponse == null || réponse.length() == 0) {
			JOptionPane.showMessageDialog(null, "Pas de nom de sauvegarde spécifié !", "Erreur !", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			this.appMastermind.sauvegarderVueMastermindFichier(réponse, false);

			JOptionPane.showMessageDialog(null, "Sauvegarde terminée !", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
		
		} catch (FileAlreadyExistsException e) {
			switch (JOptionPane.showConfirmDialog(null, "Voulez-vous écraser le fichier précédent ?", "Sauvegarde",
					JOptionPane.YES_NO_OPTION)) {
			case JOptionPane.YES_OPTION:
				try {
					this.appMastermind.sauvegarderVueMastermindFichier(réponse, true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur lors de la sauvegarde écrasée !", JOptionPane.ERROR_MESSAGE);
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
