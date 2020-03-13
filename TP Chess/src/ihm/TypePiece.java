package ihm;

public enum TypePiece {
	TOUR,
	CAVALIER,
	FOU,
	REINE,
	ROI,
	PION;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
