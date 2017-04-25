import logic.Board;

public class Threads extends Thread {
	final private Board board;
	private final int timeout;

	public Threads(Board board, int timeout) {
		this.board = board;
		this.timeout = timeout;
	}

	public void run() {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(board.checkWin());
	}
}
