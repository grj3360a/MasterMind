package chessquito;

import java.util.ArrayList;
import java.util.List;

public class Position {
	
	public static List<Position> getAll(int to){
		List<Position> list = new ArrayList<Position>();

		for (int x = 0; x < to; x++)
			for (int y = 0; y < to; y++)
				list.add(new Position(x, y));
		
		return list;
	}
	
	private int x;
	private int y;

	public Position(final int l, final int c) {
		this.x = l;
		this.y = c;
	}

	public Position() {
		this(0, 0);
	}

	public int valX() {
		return this.x;
	}

	public int valY() {
		return this.y;
	}
	
	public boolean isInBound(int size) {
		return this.y >= 0 && this.y < size && this.x >= 0 && this.x < size;
	}

	@Override
	public boolean equals(final Object position) {
		boolean resultat;
		if (position instanceof Position) {
			final Position c = (Position) position;
			resultat = (this.x == c.x && this.y == c.y);
		} else {
			resultat = false;
		}
		return resultat;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
