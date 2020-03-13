package ihm;
import java.awt.Color;

public enum CouleurChess {
	NOIR(Color.BLACK),
	BLANC(Color.WHITE);

	private final Color c;
	private CouleurChess(Color c) {
		this.c = c;
	}
	
	public Color getColor() {
		return this.c;
	}
	
	public CouleurChess inverse() {
		switch(this) {
		case BLANC:
			return NOIR;
		case NOIR:
			return BLANC;
		}
		
		throw new IllegalStateException();
	}
	
	@Override
	public String toString() {
		return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
	}
}
