package chessquito.pieces;

import java.util.Arrays;
import java.util.List;

import chessquito.Position;
import ihm.CouleurChess;
import ihm.TypePiece;

public class Reine extends Piece {
	public Reine(final CouleurChess couleur) {
		super(TypePiece.REINE, couleur, 5, true);
	}

	@Override
	public List<Position> getVecteursDeplacement() {
		return Arrays.asList(new Position(-1, 0), 
							new Position(0, -1), 
							new Position(0, 1), 
							new Position(1, 0), 
							new Position(-1, -1), 
							new Position(-1, 1), 
							new Position(1, -1), 
							new Position(1, 1));
	}
}
