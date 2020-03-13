package mastermind;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mastermind.controleur.ControleurMastermind;

public class VueMastermind extends JPanel {
	private static final long serialVersionUID = 5370350225455855851L;

	private static final List<Color> COLORS = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, new Color(138, 43, 226), Color.BLACK, Color.GRAY, Color.PINK, new Color(128, 0, 0));
	
	private JTextField[] bPIHM;
	private JTextField[] combinaisonOrdiIHM;
	private JButton[][] combinaisonsJoueurIHM;
	private JButton[] paletteIHM;
	private JTextField[] mPIHM;
	private JButton valider;
	
	private int nbCouleurs;
	private int taille;
	
	public VueMastermind(int nbCouleurs, int taille) {
		super(new BorderLayout());
		ControleurMastermind contrl = new ControleurMastermind(this, taille, nbCouleurs);
		this.nbCouleurs = nbCouleurs;
		this.taille = taille;
		
		JPanel upper = new JPanel(new FlowLayout());
		this.add(upper, BorderLayout.NORTH);
		upper.add(new JLabel("Couleurs :"));
		
		JPanel upperColors = new JPanel(new GridLayout(1, this.nbCouleurs));
		upper.add(upperColors);
		
		this.paletteIHM = new JButton[this.nbCouleurs];
		for (int i = 0; i < this.nbCouleurs; i++) {
			upperColors.add(this.paletteIHM[i] = new JButton());
			this.paletteIHM[i].setBackground(chiffreEnCouleur(i));
			this.paletteIHM[i].addActionListener(contrl);
		}
		
		JPanel middle = new JPanel(new GridLayout(ControleurMastermind.NBMAX_COMBINAISONS, 2));
		this.add(middle);
		
		this.bPIHM = new JTextField[ControleurMastermind.NBMAX_COMBINAISONS];
		this.mPIHM = new JTextField[ControleurMastermind.NBMAX_COMBINAISONS];
		this.combinaisonsJoueurIHM = new JButton[ControleurMastermind.NBMAX_COMBINAISONS][];
		for (int i = 0; i < ControleurMastermind.NBMAX_COMBINAISONS; i++) {
			JPanel lineProposition = new JPanel(new GridLayout(1, this.taille));
			middle.add(lineProposition);
			
			this.combinaisonsJoueurIHM[i] = new JButton[this.taille];
			for (int j = 0; j < this.taille; j++) {
				lineProposition.add(this.combinaisonsJoueurIHM[i][j] = new JButton());
				this.combinaisonsJoueurIHM[i][j].setEnabled(i == 0);
				this.combinaisonsJoueurIHM[i][j].setBackground(Color.WHITE);
				this.combinaisonsJoueurIHM[i][j].addActionListener(contrl);
			}
			
			JPanel lineReponse = new JPanel(new GridLayout(2, 2));
			middle.add(lineReponse);
			
			lineReponse.add(new JLabel("BP"));
			lineReponse.add(new JLabel("MP"));
			lineReponse.add(this.bPIHM[i] = new JTextField());
			this.bPIHM[i].setEditable(false);
			lineReponse.add(this.mPIHM[i] = new JTextField());
			this.mPIHM[i].setEditable(false);
		}
		
		JPanel bottom = new JPanel(new GridLayout(1, 2));
		this.add(bottom, BorderLayout.SOUTH);
		JPanel combiOrdi = new JPanel(new GridLayout(1, 4));
		bottom.add(combiOrdi);
		
		this.combinaisonOrdiIHM = new JTextField[this.taille];
		for (int i = 0; i < this.taille; i++) {
			combiOrdi.add(this.combinaisonOrdiIHM[i] = new JTextField());
			this.combinaisonOrdiIHM[i].setEditable(false);
			this.combinaisonOrdiIHM[i].setBackground(Color.WHITE);
		}
		
		this.valider = new JButton("Valider");
		valider.addActionListener(contrl);
		bottom.add(valider);
	}
	
	public void activerCombinaison(int i) {
		for (int j = 0; j < ControleurMastermind.NBMAX_COMBINAISONS; j++)
			désactiverCombinaison(j);
		
		if(i < 0 || i >= ControleurMastermind.NBMAX_COMBINAISONS)
			return;
		
		for (int j = 0; j < this.taille; j++)
			this.combinaisonsJoueurIHM[i][j].setEnabled(true);
	}
	
	public void désactiverCombinaison(int i) {
		for (int j = 0; j < this.taille; j++)
			this.combinaisonsJoueurIHM[i][j].setEnabled(false);
	}
	
	public void afficherBP(int i, int nbBP) {
		this.bPIHM[i].setText(nbBP + "");
	}
	
	public void afficherMP(int i, int nbMP) {
		this.mPIHM[i].setText(nbMP + "");
	}
	
	public void afficherCombinaisonOrdinateur(int[] tableauCouleurs) {
		if(tableauCouleurs.length != this.taille) throw new IllegalArgumentException("La réponse ne fait pas la bonne taille.");
		for (int i = 0; i < this.taille; i++) {
			this.combinaisonOrdiIHM[i].setBackground(chiffreEnCouleur(tableauCouleurs[i]));
		}
	}
	
	public boolean appartientCombinaison(JButton b, int i) {
		for (int j = 0; j < this.combinaisonsJoueurIHM[i].length; j++)
			if(b == this.combinaisonsJoueurIHM[i][j])
				return true;
		return false;
	}
	
	public boolean appartientPalette(JButton b) {
		for (int i = 0; i < this.paletteIHM.length; i++)
			if(this.paletteIHM[i] == b)
				return true;
		return false;
	}
	
	public boolean estBoutonValider(JButton b) {
		return b == this.valider;
	}
	
	public boolean combinaisonComplete(int i) {
		for (int j = 0; j < this.combinaisonsJoueurIHM[i].length; j++)
			if(this.combinaisonsJoueurIHM[i][j].getBackground() == Color.WHITE)
				return false;
		
		return true;
	}
	
	public int[] combinaisonEnEntiers(int i) {
		int[] combi = new int[this.taille];
		
		for (int j = 0; j < this.combinaisonsJoueurIHM[i].length; j++)
			combi[j] = couleurEnChiffre(this.combinaisonsJoueurIHM[i][j].getBackground());
		
		return combi;
	}
	
	public int getNbCouleurs() {
		return this.nbCouleurs;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public static int couleurEnChiffre(Color c) {
		if(COLORS.contains(c)) 
			return COLORS.indexOf(c);
		return Integer.MAX_VALUE;
	}
	
	public static Color chiffreEnCouleur(int i) {
		if(i < 0 || i >= COLORS.size())
			return Color.WHITE;
		return COLORS.get(i);
	}
	
}
