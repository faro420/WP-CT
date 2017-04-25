
import static org.junit.Assert.assertEquals;

import javax.swing.JButton;
import org.junit.Test;

import gfx.MainWindow;
import gfx.Ressources;
import logic.Board;
import logic.Player;
import logic.WinState;

/*
 * 
 * @todo
 * in general the tests are lacking going through more than one KMN-combination
 * and also trying setting tokens on all possible fields instead of only 0,0 and 0,1...
 * 
 */

public class BoardUnitTest {

	private static final int TIMEOUT = 0;

	@Test
	public void testNextTurn() throws InterruptedException {
		Board board = initBoard();
		Player activePlayer = board.getActivePlayer();
		assertEquals("Player 1", activePlayer.getName());
		board.nextTurn();
		activePlayer = board.getActivePlayer();
		assertEquals("Player 2", activePlayer.getName());
	}

	@Test
	public void testGetActivePlayer() throws InterruptedException {
		Board board = initBoard();
		Player activePlayer = board.getActivePlayer();
		assertEquals("Player 1", activePlayer.getName());
	}
	
	@Test
	public void testSetToken2dGet2d() throws InterruptedException {
		Board board = initBoard();
		assert(0 == board.get2d(0, 0));
		board.setToken2d(0, 0, board.getActivePlayer());
		// assuming that active player is player one...
		assert(1 == board.get2d(0, 0));

		board.nextTurn();
		
		assert(0 == board.get2d(0, 1));
		board.setToken2d(0, 1, board.getActivePlayer());
		// assuming that the next active player is player two...
		assert(2 == board.get2d(0, 1));
	}
	
	@Test
	public void testGetKMN() throws InterruptedException {
		Board board = initBoard();
		assert(3 == board.getK());
		assert(3 == board.getM());
		assert(3 == board.getN());
	}
	
	@Test
	public void testSetGetKMN() throws InterruptedException {
		Board board = initBoard();
		assert(3 == board.getK());
		assert(3 == board.getM());
		assert(3 == board.getN());
		board.setK(4);
		board.setM(4);
		board.setN(4);
		assert(4 == board.getK());
		assert(4 == board.getM());
		assert(4 == board.getN());
	}

	@Test
	public void testGetPlayerNameInField() throws InterruptedException {
		Board board = initBoard();
		board.setToken2d(0, 0, board.getActivePlayer());
		// assuming that active player is player one...
		// and assuming that "Player 1" was set in initBoard @todo maybe use constant (?)
		assertEquals("Player 1",board.getPlayerNameInField(0, 0));
	}
	
	@Test
	public void testGetSize() throws InterruptedException {
		Board board = initBoard();
		// assuming getSize returns K * M
		assert(9 == board.getSize());
	}
	
	@Test
	public void testResetGame() throws InterruptedException {
		Board board = initBoard();
		assert(0 == board.get2d(0, 0));
		board.setToken2d(0, 0, board.getActivePlayer());
		assert(1 == board.get2d(0, 0));
		board.nextTurn();
		assert(0 == board.get2d(0, 1));
		board.setToken2d(0, 1, board.getActivePlayer());
		assert(2 == board.get2d(0, 1));
		
		board.resetGame();
		assert(0 == board.get2d(0, 0));
		assert(0 == board.get2d(0, 0));
	}
	
	/*
	 * @todo check if all directions result in a win...
	 */
	@Test
	public void testCheckWinNone() throws InterruptedException {
		Board board = initBoard();
		assertEquals(WinState.none, board.checkWin());
	}
	
	@Test
	public void testCheckWinP1() throws InterruptedException {
		Board board = initBoard();
		assertEquals(WinState.none, board.checkWin());
		board.setToken2d(0, 0, board.getActivePlayer());
		board.nextTurn();
		board.setToken2d(0, 1, board.getActivePlayer());
		board.nextTurn();
		board.setToken2d(1, 0, board.getActivePlayer());
		board.nextTurn();
		board.setToken2d(0, 2, board.getActivePlayer());
		board.nextTurn();
		board.setToken2d(2, 0, board.getActivePlayer());
		assertEquals(WinState.player1, board.checkWin());
	}
	
	@Test
	public void testCheckWinP2() throws InterruptedException {
		Board board = initBoard();
		assertEquals(WinState.none, board.checkWin());
		board.setToken2d(0, 0, board.getActivePlayer());//P1
		board.nextTurn();
		board.setToken2d(0, 1, board.getActivePlayer());//P2
		board.nextTurn();
		board.setToken2d(1, 0, board.getActivePlayer());//P1
		board.nextTurn();
		board.setToken2d(1, 1, board.getActivePlayer());//P2
		board.nextTurn();
		board.setToken2d(0, 2, board.getActivePlayer());//P1
		board.nextTurn();
		board.setToken2d(2, 1, board.getActivePlayer());//P2
		assertEquals(WinState.player2, board.checkWin());
	}
	
	@Test
	public void testCheckWinTie() throws InterruptedException {
		Board board = initBoard();
		assertEquals(WinState.none, board.checkWin());
		board.setToken2d(0, 0, board.getActivePlayer());//P1
		board.nextTurn();
		board.setToken2d(0, 1, board.getActivePlayer());//P2
		board.nextTurn();
		board.setToken2d(0, 2, board.getActivePlayer());//P1
		board.nextTurn();
		board.setToken2d(1, 0, board.getActivePlayer());//P2
		board.nextTurn();
		board.setToken2d(1, 1, board.getActivePlayer());//P1
		board.nextTurn();
		board.setToken2d(1, 2, board.getActivePlayer());//P2
		board.nextTurn();
		board.setToken2d(2, 1, board.getActivePlayer());//P1
		board.nextTurn();
		board.setToken2d(2, 0, board.getActivePlayer());//P2
		assertEquals(WinState.tie, board.checkWin());
	}
	
	
	/*
	 * returns a board with Player 1 and Player 2
	 * 
	 */
	private Board initBoard() {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);
		return board;
	}
}
