package ihm;
import javax.swing.ImageIcon;

public class PieceIHM extends ImageIcon {
	private static final long serialVersionUID = 4719553321080145951L;

	private CouleurChess couleurPiece;
	private TypePiece nomPiece;
	private TypeImage typeImage;
	
	/**
	 * construit une piece graphique du jeu de chessquito. La piece est une image produite à partir d'un fichier .gif contenu dans le repertoire images
	 * @param nom nom de la piece ("dame", "tour", "fou", "cavalier")
	 * @param couleur couleur de la piece ("Noir", "Blanc")
	 * @param type type d'image ("Fixe", "Anime")
	 */
	public PieceIHM(TypePiece nom, CouleurChess couleur, TypeImage type) {
		super(PieceIHM.class.getResource("/images/" + nom.toString() + couleur.toString() + type.toString() + ".gif"));
		this.nomPiece = nom;
		this.couleurPiece = couleur;
		this.typeImage = type;
	}

	public CouleurChess getCouleurPiece() {
		return couleurPiece;
	}

	public TypePiece getNomPiece() {
		return nomPiece;
	}

	public TypeImage getTypeImage() {
		return typeImage;
	}
	
}
