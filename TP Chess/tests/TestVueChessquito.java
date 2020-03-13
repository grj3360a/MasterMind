import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import chessquito.Position;
import ihm.CaseIHM;
import ihm.CouleurChess;
import ihm.PieceIHM;
import ihm.TypeImage;
import ihm.TypePiece;
import ihm.VueChessquito;

public class TestVueChessquito {

	private static final int SIZE = 4;
	private VueChessquito vue;
	
	@Before
	public void setUp() throws Exception {
		this.vue = new VueChessquito(SIZE);
	}

	@After
	public void tearDown() throws Exception {
		this.vue = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorException() {
		this.vue = new VueChessquito(-1);
	}

	@Test
	public void testPositionnerPieceExceptionPieceNull() {
		this.vue.positionnerPiece(null, new Position(1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionnerPieceExceptionPlacementInf1() {
		this.vue.positionnerPiece(new PieceIHM(TypePiece.CAVALIER, CouleurChess.BLANC, TypeImage.ANIME), new Position(-1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionnerPieceExceptionPlacementInf2() {
		this.vue.positionnerPiece(new PieceIHM(TypePiece.CAVALIER, CouleurChess.BLANC, TypeImage.ANIME), new Position(1, -1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionnerPieceExceptionPlacementSup1() {
		this.vue.positionnerPiece(new PieceIHM(TypePiece.CAVALIER, CouleurChess.BLANC, TypeImage.ANIME), new Position(SIZE, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionnerPieceExceptionPlacementSup2() {
		this.vue.positionnerPiece(new PieceIHM(TypePiece.CAVALIER, CouleurChess.BLANC, TypeImage.ANIME), new Position(1, SIZE));
	}
	
	@Test
	public void testPanels() {
		assertEquals(2, this.vue.getComponentCount());
		assertThat(this.vue.getLayout(), instanceOf(BorderLayout.class));
		
		assertThat(this.vue.getComponent(0), instanceOf(JPanel.class));
		assertThat(this.vue.getComponent(1), instanceOf(JScrollPane.class));
	}
	
	@Test
	public void testPlateau() {
		JPanel plateau = (JPanel) this.vue.getComponent(0);

		assertEquals(SIZE * SIZE, plateau.getComponentCount());
		int i = 0;
		CouleurChess couleur = CouleurChess.NOIR;
		for(Component c : plateau.getComponents()) {
			assertThat(c, instanceOf(CaseIHM.class));
			CaseIHM cas = (CaseIHM) c;
			if(i > 0 && i % SIZE == 0) couleur = couleur.inverse();
			assertEquals("i:" + i, couleur = couleur.inverse(), cas.getCouleur());
			i++;
		}
	}
	
	@Test
	public void testChampsTexte() {
		JScrollPane scroll = (JScrollPane) this.vue.getComponent(1);
		JViewport p1 = (JViewport) scroll.getComponent(0);

		assertThat(p1.getComponent(0), instanceOf(JTextArea.class));
	}

	@Test
	public void testPositionnerPiecePlacement() {
		final PieceIHM pi;
		this.vue.positionnerPiece(pi = new PieceIHM(TypePiece.CAVALIER, CouleurChess.BLANC, TypeImage.ANIME), new Position(1, 2));

		JPanel plateau = (JPanel) this.vue.getComponent(0);
		CaseIHM cas = (CaseIHM) plateau.getComponent(SIZE * 1 + 2);

		assertEquals(pi, cas.getIcon());
	}

	@Test
	public void testAfficherMessage() {
		JScrollPane scroll = (JScrollPane) this.vue.getComponent(1);
		JViewport p1 = (JViewport) scroll.getComponent(0);
		JTextArea textArea = (JTextArea) p1.getComponent(0);
		
		String msg = "Message de test";
		this.vue.afficherMessage(msg);
		assertEquals(msg += "\n", textArea.getText());
		
		this.vue.afficherMessage("Oui");
		assertEquals(msg += "Oui\n", textArea.getText());
	}
	
	@Test
	public void testImages() {
		for(TypeImage typeImg : TypeImage.values()) {
			for(CouleurChess coul : CouleurChess.values()) {
				for(TypePiece type : TypePiece.values()) {
					new PieceIHM(type, coul, typeImg);
				}
			}
		}
	}

}
