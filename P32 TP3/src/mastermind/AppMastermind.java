package mastermind;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class AppMastermind {
	
	public static void main(String[] args) {
		new AppMastermind();
	}
	
	//
	
	private JFrame frame;
	private VueMastermind vueMastermind;
	private int tailleCombi;
	private int nbCouleur;
	
	public AppMastermind() {
		this.tailleCombi = 4;
		this.nbCouleur = 6;
		this.vueMastermind = new VueMastermind(this.nbCouleur, this.tailleCombi);
		
		this.frame = new JFrame();
		this.frame.setTitle("Mastermind");
		this.frame.setJMenuBar(new MenuMastermind(this));
		this.frame.add(this.vueMastermind);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
		this.frame.setSize(400, 700);
		this.frame.setMinimumSize(new Dimension(300, 500));
	}
	
	public int getTailleCombi() {
		return this.tailleCombi;
	}

	public int getNbCouleur() {
		return this.nbCouleur;
	}

	public void changerItemNbCouleurs(JMenuItem nbCouleurs) {
		this.nbCouleur = Integer.parseInt(nbCouleurs.getText());
	}
	
	public void changerItemTailleCombinaison(JMenuItem tailleCombi) {
		this.tailleCombi = Integer.parseInt(tailleCombi.getText());
	}
	
	public void creerNouvelleVueMastermind() {
		final Dimension d = this.frame.getSize();
		this.frame.remove(this.vueMastermind);
		this.vueMastermind = new VueMastermind(this.nbCouleur, this.tailleCombi);
		this.frame.add(this.vueMastermind);
		this.frame.pack();
		this.frame.repaint();
		this.frame.setSize(d);
	}
	
	public void restaurerVueMastermindFichier(String nomFichier) throws ClassNotFoundException, IOException {
		File file = new File(nomFichier);
		if(!file.exists()) throw new FileNotFoundException("Le fichier n'existe pas !");
		if(!file.canRead()) throw new FileNotFoundException("Fichier illisible !");

		this.frame.remove(this.vueMastermind);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		this.vueMastermind = (VueMastermind) in.readObject();
		in.close();
		this.frame.add(this.vueMastermind);
		this.frame.repaint();
	}
	
	public void sauvegarderVueMastermindFichier(String nomFichier, boolean crush) throws IOException {
		File file = new File(nomFichier);
		if(!crush && file.exists()) throw new FileAlreadyExistsException("Le fichier existe déjà !");
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(this.vueMastermind);
		out.close();
	}
	
}
