package mastermind;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mastermind.controleur.ControleurQuitter;
import mastermind.controleur.ControleurRejouer;
import mastermind.controleur.ControleurRestaurer;
import mastermind.controleur.ControleurSauvegarder;

public class MenuMastermind extends JMenuBar {
	private static final long serialVersionUID = -7341092340837383816L;
	private final AppMastermind appMastermind;
	
	public MenuMastermind(AppMastermind appMastermind) {
		this.appMastermind = appMastermind;
		
		JMenu jeu = new JMenu("Jeu");
		this.add(jeu);
		
		JMenuItem rejouer = new JMenuItem("Rejouer");
		rejouer.addActionListener(new ControleurRejouer(this.appMastermind));
		jeu.add(rejouer);
		
		JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
		sauvegarder.addActionListener(new ControleurSauvegarder(this.appMastermind));
		jeu.add(sauvegarder);

		JMenuItem rest = new JMenuItem("Restaurer");
		rest.addActionListener(new ControleurRestaurer(this.appMastermind));
		jeu.add(rest);
		
		jeu.addSeparator();

		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ControleurQuitter(this.appMastermind));
		jeu.add(quit);
		
		//
		
		JMenu options = new JMenu("Options");
		this.add(options);
		
		JMenu nbCouleurs = new JMenu("Nombre de couleurs");
		options.add(nbCouleurs);
		for (int i = 2; i <= 10; i++) {
			JMenuItem iCouleur = new JMenuItem(i + "");
			iCouleur.addActionListener((ae) -> {this.appMastermind.changerItemNbCouleurs(iCouleur);});
			nbCouleurs.add(iCouleur);
		}
		
		JMenu tailleCombi = new JMenu("Taille combinaison");
		options.add(tailleCombi);
		for (int i = 2; i <= 10; i++) {
			JMenuItem iCombi = new JMenuItem(i + "");
			iCombi.addActionListener((ae) -> {this.appMastermind.changerItemTailleCombinaison(iCombi);});
			tailleCombi.add(iCombi);
		}
	}
	
}
