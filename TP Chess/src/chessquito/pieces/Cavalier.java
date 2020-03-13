// 

// 

package chessquito.pieces;

import java.util.Arrays;
import java.util.List;

import chessquito.Position;
import ihm.CouleurChess;
import ihm.TypePiece;

public class Cavalier extends Piece {
	public Cavalier(final CouleurChess couleur) {
		super(TypePiece.CAVALIER, couleur, 3, false);
	}

	@Override
	public List<Position> getVecteursDeplacement() {
		return Arrays.asList(
				new Position(-2, -1), 
				new Position(-1, -2), 
				new Position(1, -2), 
				new Position(2, -1), 
				new Position(-2, 1), 
				new Position(-1, 2), 
				new Position(1, 2), 
				new Position(2, 1));
	}
}
