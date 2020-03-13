package chessquito.pieces;

import java.util.Arrays;
import java.util.List;

import chessquito.Position;
import ihm.CouleurChess;
import ihm.TypePiece;

public class Tour extends Piece {
	public Tour(final CouleurChess couleur) {
		super(TypePiece.TOUR, couleur, 4, true);
	}

	@Override
	public List<Position> getVecteursDeplacement() {
		return Arrays.asList(
				new Position(-1, 0), 
				new Position(0, -1), 
				new Position(0, 1), 
				new Position(1, 0)
						);
	}
}
