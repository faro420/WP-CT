import static org.junit.Assert.*;

import javax.swing.JButton;
import org.junit.Test;

import gfx.MainWindow;
import gfx.Ressources;
import logic.Board;
import logic.Player;
import logic.WinState;

public class Test1 {

	private static final int TIMEOUT = 500;

	@Test
	public void testIconCannotBeOverwritten() throws InterruptedException {
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
	public void test2() throws InterruptedException {
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
		Threads thread = new Threads(board, 500);
		thread.start();
		clickButton(frame, 2, 0);// Player2 (2,0)
		System.out.println(board.checkWin());
		assertEquals(WinState.player2, board.checkWin());
	}

	@Test
	public void testCheckWincondition() throws InterruptedException {
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
		Threads thread = new Threads(board, 0);
		thread.start();
		assertEquals(WinState.player1, board.checkWin());
	}

	private void clickButton(MainWindow frame, int x, int y) throws InterruptedException {
		Thread.sleep(TIMEOUT);
		JButton b = frame.getButtonArr()[x][y];
		b = frame.getButtonArr()[x][y];
		b.doClick();
		Thread.sleep(TIMEOUT);
	}
}
