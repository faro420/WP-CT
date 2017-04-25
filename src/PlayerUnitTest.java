
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gfx.Ressources;
import logic.Player;


public class PlayerUnitTest {

	@Test
	public void testGetIcon() throws InterruptedException {
		Player testPlayer = new Player("Name", Ressources.icon_x);
		assertEquals(Ressources.icon_x, testPlayer.getIcon());
	}

	@Test
	public void testGetIconString() throws InterruptedException {
		Player testPlayer = new Player("TestName", Ressources.icon_x);
		//How do I know that the IconString of Ressources.icon_x is "X"
		assertEquals("X", testPlayer.getIconString());
	}

	@Test
	public void testSetGetIconString() throws InterruptedException {
		Player testPlayer = new Player("TestName", Ressources.icon_x);
		assertEquals(Ressources.icon_x, testPlayer.getIcon());
		testPlayer.setIcon(Ressources.icon_tessa_blue);
		assertEquals(Ressources.icon_tessa_blue, testPlayer.getIcon());
	}

	@Test
	public void testGetName() throws InterruptedException {
		Player testPlayer = new Player("TestName", Ressources.icon_x);
		assertEquals("TestName", testPlayer.getName());
	}

	@Test
	public void testToString() throws InterruptedException {
		Player testPlayer = new Player("TestName", Ressources.icon_x);
		//How do I know how the toString()-method ought to work? (except for System.out.println(testPlayer.toString())?)
		assertEquals("[TestName]", testPlayer.toString());
	}
}
