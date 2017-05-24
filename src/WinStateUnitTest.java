package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.WinState;


public class WinStateUnitTest {


	@Test
	public void testValues() throws InterruptedException {
		WinState[] testWS = WinState.values();
		assertEquals(WinState.none, testWS[0]);
		assertEquals(WinState.player1, testWS[1]);
		assertEquals(WinState.player2, testWS[2]);
		assertEquals(WinState.tie, testWS[3]);
		
		assertEquals(4,testWS.length);
	}

	@Test
	public void testValueOf() throws InterruptedException {
		assertEquals(WinState.none, WinState.valueOf("none"));
		assertEquals(WinState.player1, WinState.valueOf("player1"));
		assertEquals(WinState.player2, WinState.valueOf("player2"));
		assertEquals(WinState.tie, WinState.valueOf("tie"));
	}
}
