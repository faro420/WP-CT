import static org.junit.Assert.*;

import javax.swing.JButton;
import org.junit.Test;

import gfx.MainWindow;
import gfx.Ressources;
import logic.Board;
import logic.Player;
import logic.WinState;

public class MainTest {

	private static final int TIMEOUT = 50;

	@Test
	public void test1_IconCannotBeOverwritten() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame = new MainWindow(p1, p2, board);
		frame.setVisible(true);
		clickButton(frame, 0, 0);
		clickButton(frame, 0, 0);
		assertEquals(p1, board.getPlayer2d(0, 0));
	}

	@Test
	public void test2_Player2ScoreNotUpdated() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame;
		frame = new MainWindow(p1, p2, board);
		frame.setVisible(true);

		clickButton(frame, 0, 1);// Player1 (0,1)
		clickButton(frame, 0, 0);// Player2 (0,0)
		clickButton(frame, 0, 2);// Player1 (0,2)
		clickButton(frame, 1, 0);// Player2 (1,0)
		clickButton(frame, 1, 1);// Player1 (1,1)
		clickButton(frame, 2, 0);// Player2 (2,0)
		assertEquals(frame.getPlayer2_score().getText(), "01");
	}

	@Test
	public void test3_Player2ScoreWeirdUpdate() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(6, 6, 3, p1, p2);

		final MainWindow frame;
		frame = new MainWindow(p1, p2, board);
		frame.setVisible(true);

		clickButton(frame, 0, 1);// Player1 (0,1)
		clickButton(frame, 0, 0);// Player2 (0,0)
		clickButton(frame, 0, 2);// Player1 (0,2)
		clickButton(frame, 1, 0);// Player2 (1,0)
		clickButton(frame, 1, 1);// Player1 (1,1)
		clickButton(frame, 2, 0);// Player2 (2,0)
		assertEquals(frame.getPlayer2_score().getText(), "01");

		clickButton(frame, 0, 1);// Player1 (0,1)
		clickButton(frame, 0, 0);// Player2 (0,0)
		clickButton(frame, 0, 2);// Player1 (0,2)
		clickButton(frame, 1, 0);// Player2 (1,0)
		clickButton(frame, 1, 1);// Player1 (1,1)
		clickButton(frame, 2, 0);// Player2 (2,0)
		assertEquals(frame.getPlayer2_score().getText(), "02");
	}

	@Test
	public void test4_Player1ScoreWeirdUpdate() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame = new MainWindow(p1, p2, board);
		frame.setVisible(true);

		clickButton(frame, 0, 2);
		clickButton(frame, 0, 1);
		clickButton(frame, 1, 2);
		clickButton(frame, 1, 1);
		clickButton(frame, 2, 2);
		assertEquals(frame.getPlayer1_score().getText(), "01");
	}

	@Test
	public void test5_WinConditionNotSatisfied() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame = new MainWindow(p1, p2, board);
		frame.setVisible(true);

		clickButton(frame, 0, 2);
		clickButton(frame, 1, 1);
		clickButton(frame, 1, 2);
		clickButton(frame, 0, 1);
		clickButton(frame, 2, 2);
		assertTrue(board.checkWin() == WinState.player1);
	}

	private void clickButton(MainWindow frame, int x, int y) throws InterruptedException {
		Thread.sleep(TIMEOUT);
		JButton b = frame.getButtonArr()[x][y];
		b = frame.getButtonArr()[x][y];
		b.doClick();
		Thread.sleep(TIMEOUT);
	}
}
