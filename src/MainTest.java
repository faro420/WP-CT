package tests;

import static org.junit.Assert.*;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSpinner;

import org.junit.Test;

import gfx.MainWindow;
import gfx.Ressources;
import logic.Board;
import logic.Player;
import logic.WinState;

public class MainTest {

	private static final int TIMEOUT = 10;

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
		frame.checkWinner(WinState.player1);
	}

	@Test
	public void testRessources() throws InterruptedException {
		Ressources emptyRessource = new Ressources();
	}

	@Test
	public void testSettingsFrame() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame = new MainWindow(p1, p2, board);
		frame.settingsFrame();
	}
	
	@Test
	public void testCheckWinnerTie() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		
		board.setToken2d(0, 0, p1);
		board.setToken2d(0, 1, p2);
		board.setToken2d(0, 2, p1);
		board.setToken2d(1, 0, p2);
		board.setToken2d(1, 1, p1);
		board.setToken2d(1, 2, p2);
		board.setToken2d(2, 1, p1);
		board.setToken2d(2, 0, p2);
		
		final MainWindow frame = new MainWindow(p1, p2, board);
		frame.checkWinner(WinState.tie);
	}
	
	@Test
	public void testDebug() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame = new MainWindow(p1, p2, board);
		MainWindow.setDebugg(true);
		
		frame.turn(1,1);
	}
	
	@Test
	public void testNewActionListener1() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame = new MainWindow(p1, p2, board);
		JMenuBar menuBar = (JMenuBar)(frame.getContentPane().getComponent(0));
		JMenu mnNewMenu = (JMenu)menuBar.getComponent(0);
		JMenuItem configMenuItem = (JMenuItem)mnNewMenu.getItem(0);
		configMenuItem.doClick();
	}
	
	@Test
	public void testNewActionListenerSettings() throws InterruptedException {
		Player p1 = new Player("Player 1", Ressources.icon_x);
		Player p2 = new Player("Player 2", Ressources.icon_o);
		Board board = new Board(3, 3, 3, p1, p2);

		final MainWindow frame = new MainWindow(p1, p2, board);
		JMenuBar menuBar = (JMenuBar)(frame.getContentPane().getComponent(0));
		JMenu mnNewMenu = (JMenu)menuBar.getComponent(0);
		JMenuItem configMenuItem = (JMenuItem)mnNewMenu.getItem(0);
		configMenuItem.doClick();
		
		Frame[] frames = Frame.getFrames();
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		for(Frame one_frame : frames){
			if(one_frame.getTitle().equals("Settings")){
				JPanel contentPane = (JPanel)(((JFrame) one_frame).getContentPane());
				JPanel tmpPane = (JPanel)(contentPane.getComponent(0));

				JSpinner spinner_m = (JSpinner) (tmpPane.getComponent(1));
				spinner_m.setValue(-5);
				JSpinner spinner_n = (JSpinner) (tmpPane.getComponent(3));
				spinner_n.setValue(-5);
				
				JButton b_save = (JButton) (tmpPane.getComponent(10));
				b_save.doClick();
				System.out.println(b_save.getText());
			}
		}
		
		//eigentlich eigene 
		for(Frame one_frame : frames){
			if(one_frame.getTitle().equals("Settings")){
				JPanel contentPane = (JPanel)(((JFrame) one_frame).getContentPane());
				JPanel tmpPane = (JPanel)(contentPane.getComponent(0));

				JSpinner spinner_m = (JSpinner) (tmpPane.getComponent(1));
				spinner_m.setValue(5);
				JSpinner spinner_n = (JSpinner) (tmpPane.getComponent(3));
				spinner_n.setValue(5);
				
				JButton b_save = (JButton) (tmpPane.getComponent(10));
				b_save.doClick();
				System.out.println(b_save.getText());
			}
		}
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}
	
	
	private void clickButton(MainWindow frame, int x, int y) throws InterruptedException {
		Thread.sleep(TIMEOUT);
		JButton b = frame.getButtonArr()[x][y];
		b = frame.getButtonArr()[x][y];
		b.doClick();
		Thread.sleep(TIMEOUT);
	}
}
