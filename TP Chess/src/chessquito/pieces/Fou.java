package chessquito.pieces;

import java.util.Arrays;
import java.util.List;

import chessquito.Position;
import ihm.CouleurChess;
import ihm.TypePiece;

public class Fou extends Piece {
	public Fou(final CouleurChess couleur) {
		super(TypePiece.FOU, couleur, 2, true);
	}

	@Override
	public List<Position> getVecteursDeplacement() {
		return Arrays.asList(
				new Position(-1, -1), 
				new Position(-1, 1), 
				new Position(1, -1), 
				new Position(1, 1)
							);
	}
}
