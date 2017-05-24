package tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gfx.MainWindow;
import gfx.Ressources;
import logic.Board;
import logic.Player;
import logic.WinState;

public class Test01 {
  private Player p1, p2;
  private Board board;
  private MainWindow frame;

  @Before
  public void setUp() throws Exception {
    p1 = new Player("Player 1", Ressources.icon_x);
    p2 = new Player("Player 2", Ressources.icon_o);
    board = new Board(3, 3, 3, p1, p2);
    frame = new MainWindow(p1, p2, board);
    frame.setVisible(true);
  }

  @After
  public void tearDown() throws Exception {
    p1 = p2 = null;
    board = null;
    frame = null;
  }

  @Test
  public void test() throws InterruptedException {
    frame.turn(0, 0);
    frame.turn(0, 1);
    frame.turn(1, 0);
    frame.turn(0, 2);
    WinState winner = frame.turn(2, 0);
    assertTrue(WinState.player1 == winner);
  }

}
